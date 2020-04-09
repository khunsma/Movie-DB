package com.khunsma.executor

import io.reactivex.Scheduler

interface BackgroundThread{

    fun getScheduler(): Scheduler

}