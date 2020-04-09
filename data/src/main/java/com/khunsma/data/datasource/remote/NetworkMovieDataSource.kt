package com.khunsma.data.datasource.remote

import com.google.gson.Gson
import com.khunsma.data.BuildConfig
import com.khunsma.data.network.RestAdapter
import com.khunsma.data.network.service.MovieService
import com.khunsma.data.response.*
import com.khunsma.exception.ApiException
import com.khunsma.exception.NetworkException
import io.reactivex.Observable
import java.io.IOException

class NetworkMovieDataSource{

    private val movieService = RestAdapter.get().create(MovieService::class.java)

    fun getNowPlayingMovies(page: Int?): Observable<NowPlayingMovieResponse>{
        return movieService.getNowPlayingMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), NowPlayingMovieResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

    fun getPopularMovies(page: Int?): Observable<PopularMovieResponse>{
        return movieService.getPopularMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try{
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), PopularMovieResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

    fun getSimilarMovies(page: Int?, movieId: Int): Observable<SimilarMovieResponse>{
        return movieService.getSimilarMovies(movieId, BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), SimilarMovieResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

    fun getTopRatedMovies(page: Int?): Observable<TopRatedMovieResponse>{
        return movieService.getTopRatedMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), TopRatedMovieResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

    fun getUpcomingMovies(page: Int?): Observable<UpComingMovieResponse>{
        return movieService.getUpComingMovies(BuildConfig.MOVIE_DB_KEY, page)
            .map {
                try {
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), UpComingMovieResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

    fun getMovieTrailer(movieId: Int): Observable<MovieTrailerResponse>{
        return movieService.getMovieTrailer(movieId, BuildConfig.MOVIE_DB_KEY)
            .map {
                try {
                    if(it.isSuccessful) it.body()
                    else {
                        val errorResponse = Gson().fromJson(it.errorBody()?.charStream(), MovieTrailerResponse::class.java)
                        throw ApiException(errorResponse.status_message)
                    }
                }catch (e: IOException){
                    throw NetworkException
                }
            }
    }

}