package com.khunsma.data.repository

import com.khunsma.data.database.entity.MovieTrailerEntity
import com.khunsma.data.datasource.cache.CacheMovieDataSource
import com.khunsma.data.datasource.remote.NetworkMovieDataSource
import com.khunsma.data.mapper.MovieMapper
import com.khunsma.domain.MovieRepository
import com.khunsma.model.movie.MovieInfo
import com.khunsma.model.movie.MovieTrailer
import io.reactivex.Observable

class MovieRepositoryImpl(
    private val networkMovieDataSource: NetworkMovieDataSource,
    private val cacheMovieDataSource: CacheMovieDataSource,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getPopularMovieList(page: Int?): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getPopularMovies(page)
            .map { movieMapper.mapMovieInfoList(it.results) }
    }

    override fun getSimilarMovieList(page: Int?, movieId: Int): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getSimilarMovies(page, movieId)
            .map { movieMapper.mapMovieInfoList(it.results) }
    }

    override fun getTopRatedMovieList(page: Int?): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getTopRatedMovies(page)
            .map { movieMapper.mapMovieInfoList(it.results) }
    }

    override fun getNowPlayingMovieList(page: Int?): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getNowPlayingMovies(page)
            .map { movieMapper.mapMovieInfoList(it.results) }
    }

    override fun getUpcomingMovieList(page: Int?): Observable<List<MovieInfo>> {
        return networkMovieDataSource.getUpcomingMovies(page)
            .map { movieMapper.mapMovieInfoList(it.results) }
    }

    override fun getComingSoonMovieList(): Observable<List<MovieTrailer>> {

        return Observable.concat(
            cacheMovieDataSource.getAllMovieTrailer()
                .flatMap {
                    if (it.isEmpty()) fetchUpComingMovieFromRemote()
                    else Observable.just(movieMapper.mapMovieTrailerList(it))
                }, fetchUpComingMovieFromRemote()
            )

    }

    private fun fetchUpComingMovieFromRemote(): Observable<List<MovieTrailer>> {
        return networkMovieDataSource.getUpcomingMovies(null)
            .map { it.results }
            .flatMapIterable { it }
            .flatMap { m ->
                networkMovieDataSource.getMovieTrailer(m.id)
                    .filter { it.results.isNotEmpty() }
                    .flatMap {
                        var key: String = ""
                        for (result in it.results) {
                            if (result.site == "YouTube" && result.type == "Trailer") {
                                key = result.key
                                break
                            }
                        }
                        Observable.just(
                            MovieTrailerEntity(
                                m.id,
                                m.title,
                                m.overview,
                                key,
                                m.release_date,
                                m.backdrop_path,
                                m.backdrop_path
                            )
                        )
                    }
            }.toList().toObservable()
            .flatMap { cacheMovieDataSource.saveMovieTrailers(it) }
            .map { movieMapper.mapMovieTrailerList(it) }
    }
}