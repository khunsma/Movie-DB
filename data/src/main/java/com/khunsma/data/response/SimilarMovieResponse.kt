package com.khunsma.data.response

import com.khunsma.data.response.MovieInfoResponse

data class SimilarMovieResponse(val page: Int,
                                val total_results: Int,
                                val total_pages: Int,
                                val results: List<MovieInfoResponse>,
                                val status_code: Int,
                                val status_message: String)