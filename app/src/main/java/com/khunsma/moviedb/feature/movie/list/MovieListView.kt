package com.khunsma.moviedb.feature.movie.list

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.khunsma.interactor.MovieListInteractor
import com.khunsma.viewstate.MovieListViewState
import io.reactivex.Observable

interface MovieListView: MvpView{

    fun render(viewState: MovieListViewState)

    fun popularMovieListIntent(): Observable<Int>

    fun nowPlayingMovieListIntent(): Observable<Int>

    fun similarMovieListIntent(): Observable<MovieListInteractor.SimilarMovieIntent>

    fun topRatedMovieListIntent(): Observable<Int>

    fun upComingMovieListIntent(): Observable<Int>
}