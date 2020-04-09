package com.khunsma.executor

import io.reactivex.Scheduler

interface PostExecutionThread{

    fun getScheduler(): Scheduler
}