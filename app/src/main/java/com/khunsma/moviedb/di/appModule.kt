package com.khunsma.moviedb.di

import com.khunsma.data.database.entity.RoomDbHelper
import com.khunsma.data.executor.BackgroundThreadImpl
import com.khunsma.data.executor.JobsExecutor
import com.khunsma.data.utils.DateUtils
import com.khunsma.executor.BackgroundThread
import com.khunsma.executor.PostExecutionThread
import com.khunsma.moviedb.executor.UiThread
import org.koin.dsl.module.module

val appModule = module {

    single { UiThread() as PostExecutionThread }

    single<BackgroundThread> { BackgroundThreadImpl(JobsExecutor()) }

    single { DateUtils() }

    single { RoomDbHelper(get()) }

}