package com.khunsma.moviedb.di

import com.khunsma.data.repository.MovieRepositoryImpl
import com.khunsma.domain.MovieRepository
import org.koin.dsl.module.module

val repositoryModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }

}