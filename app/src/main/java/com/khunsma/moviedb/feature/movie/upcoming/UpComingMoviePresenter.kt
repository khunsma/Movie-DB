package com.khunsma.moviedb.feature.movie.upcoming

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.khunsma.interactor.UpComingMovieInteractor
import com.khunsma.viewstate.UpComingMovieViewState
import io.reactivex.Observable

class UpComingMoviePresenter(private val upComingMovieInteractor: UpComingMovieInteractor)
    : MviBasePresenter<UpComingMovieView, UpComingMovieViewState>(){

    override fun bindIntents() {
        val upComingMovieIntent: Observable<UpComingMovieViewState> = intent(UpComingMovieView::upComingMovieIntent)
            .flatMap { upComingMovieInteractor.executeComingSoonMovie()}

        subscribeViewState(upComingMovieIntent, UpComingMovieView::render)
    }
}