package com.khunsma.moviedb.di

import com.khunsma.data.datasource.remote.NetworkMovieDataSource
import org.koin.dsl.module.module

val networkModule = module {

    single { NetworkMovieDataSource() }
}