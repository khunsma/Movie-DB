package com.khunsma.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khunsma.data.database.entity.MovieTrailerEntity
import io.reactivex.Single


@Dao
interface MovieTrailerDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieTrailers(entities: List<MovieTrailerEntity>): List<Long>

    @Query("SELECT * FROM movie_trailer")
    fun getAllMovieTrailers(): Single<List<MovieTrailerEntity>>
}
