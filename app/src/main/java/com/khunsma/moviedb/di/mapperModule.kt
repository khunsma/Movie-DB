package com.khunsma.moviedb.di

import com.khunsma.data.mapper.MovieMapper
import org.koin.dsl.module.module

val mapperModule = module {

    single { MovieMapper(get()) }
}