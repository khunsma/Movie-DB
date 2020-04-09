package com.khunsma.data.mapper

import com.khunsma.data.database.entity.MovieTrailerEntity
import com.khunsma.data.response.MovieInfoResponse
import com.khunsma.data.utils.DateUtils
import com.khunsma.model.movie.MovieInfo
import com.khunsma.model.movie.MovieTrailer

class MovieMapper(private val dateUtils: DateUtils){

    fun mapMovieInfoList(list: List<MovieInfoResponse>): List<MovieInfo>{
        val movies = arrayListOf<MovieInfo>()
        list.forEach {
            val movieInfo = MovieInfo(it.vote_count,
                it.id,
                it.video,
                it.vote_average,
                it.title,
                it.popularity,
                "https://image.tmdb.org/t/p/w500${it.poster_path}",
                it.original_language,
                it.original_title,
                it.genre_ids,
                "https://image.tmdb.org/t/p/w500${it.backdrop_path}",
                it.adult,
                it.overview,
                dateUtils.convertDate(it.release_date))
            movies.add(movieInfo)
        }
        return movies
    }


    fun mapMovieTrailerList(entities: List<MovieTrailerEntity>): List<MovieTrailer>{
        val list = arrayListOf<MovieTrailer>()
        entities.forEach {
            list.add(MovieTrailer(it.movieId,it.title, it.overview, it.youtubeId, dateUtils.convertDate(it.releaseDate), "https://image.tmdb.org/t/p/w500${it.poster}", "https://image.tmdb.org/t/p/original${it.poster}"))
        }
        return list
    }
}