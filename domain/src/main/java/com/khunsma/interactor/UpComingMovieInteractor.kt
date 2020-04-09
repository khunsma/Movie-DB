package com.khunsma.interactor

import com.khunsma.domain.MovieRepository
import com.khunsma.executor.BackgroundThread
import com.khunsma.executor.PostExecutionThread
import com.khunsma.viewstate.UpComingMovieViewState
import io.reactivex.Observable

class UpComingMovieInteractor(private val movieRepository: MovieRepository,
                              private val mainThread: PostExecutionThread,
                              private val backgroundThread: BackgroundThread
){


    fun executeComingSoonMovie(): Observable<UpComingMovieViewState>{
        return movieRepository.getComingSoonMovieList()
            .map { UpComingMovieViewState.ComingSoonMovie(it) }
            .cast(UpComingMovieViewState::class.java)
            .onErrorReturn { UpComingMovieViewState.Error(it) }
            .startWith(UpComingMovieViewState.Progress)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }
}