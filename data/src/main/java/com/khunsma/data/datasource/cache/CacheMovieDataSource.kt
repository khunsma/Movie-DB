package com.khunsma.data.datasource.cache

import com.khunsma.data.database.entity.MovieTrailerEntity
import com.khunsma.data.database.entity.RoomDbHelper
import io.reactivex.Observable

class CacheMovieDataSource(private val dbHelper: RoomDbHelper){

    fun saveMovieTrailers(entities: List<MovieTrailerEntity>): Observable<List<MovieTrailerEntity>>{
        dbHelper.getMovieTrailerDao().insertMovieTrailers(entities)
        return dbHelper.getMovieTrailerDao().getAllMovieTrailers().toObservable()
    }

    fun getAllMovieTrailer(): Observable<List<MovieTrailerEntity>>{
        return dbHelper.getMovieTrailerDao().getAllMovieTrailers().toObservable()
    }
}