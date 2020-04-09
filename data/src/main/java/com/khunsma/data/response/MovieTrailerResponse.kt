package com.khunsma.data.response

data class MovieTrailerResponse(val id: Int,
                                val results: List<VideoInfoResponse>,
                                val status_code: Int,
                                val status_message: String)