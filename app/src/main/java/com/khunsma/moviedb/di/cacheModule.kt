package com.khunsma.moviedb.di

import com.khunsma.data.datasource.cache.CacheMovieDataSource
import org.koin.dsl.module.module

val cacheModule = module {

    single { CacheMovieDataSource(get()) }

}