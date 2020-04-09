package com.khunsma.moviedb.feature.movie.upcoming

import android.view.View
import com.bumptech.glide.Glide
import com.khunsma.moviedb.feature.base.BaseViewHolder
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.khunsma.model.movie.MovieTrailer
import kotlinx.android.synthetic.main.item_movie_trailer.view.*


class VHMovieTrailer(itemView: View): BaseViewHolder(itemView){

    fun bind(movieTrailer: MovieTrailer){
        Glide.with(itemView.context)
            .load(movieTrailer.originalPoster)
            .into(itemView.ivPoster)
        itemView.tvDate.text = movieTrailer.releaseDate
        itemView.tvTitle.text = movieTrailer.title
        itemView.tvOverview.text = movieTrailer.overview
        itemView.setOnClickListener { watchYoutubeVideo(itemView.context, movieTrailer.youtubeId) }
    }

    private fun watchYoutubeVideo(context: Context, id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }

    }
}