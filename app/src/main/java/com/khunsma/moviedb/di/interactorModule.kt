package com.khunsma.moviedb.di

import com.khunsma.interactor.MovieListInteractor
import com.khunsma.interactor.UpComingMovieInteractor
import org.koin.dsl.module.module

val interactorModule = module {

    factory { MovieListInteractor(get(), get(), get()) }

    factory { UpComingMovieInteractor(get(), get(), get()) }

}