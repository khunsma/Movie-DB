package com.khunsma.viewstate

import com.khunsma.model.movie.MovieInfo

sealed class MovieListViewState{

    class Error(val t: Throwable): MovieListViewState()

    object Progress: MovieListViewState()

    class MovieList(val movies: List<MovieInfo>): MovieListViewState()

}