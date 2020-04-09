package com.khunsma.moviedb.di

import com.khunsma.moviedb.feature.movie.list.MovieListPresenter
import com.khunsma.moviedb.feature.movie.upcoming.UpComingMoviePresenter
import org.koin.dsl.module.module

val presenterModule = module {

    factory { MovieListPresenter(get()) }

    factory { UpComingMoviePresenter(get()) }
}