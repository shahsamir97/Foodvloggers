package com.samir.foodvloggers.Trending

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.parse.ParseObject
import com.samir.foodvloggers.R

class TrendingListAdapter(val vlogs: ArrayList<ParseObject>) : RecyclerView.Adapter<TrendingListAdapter.UserViewHolder>() {




    init {
        Log.i("Adapter","Called")

    }



    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), YouTubeThumbnailView.OnInitializedListener {

        //private val USER_ID: String = "user_ID"
        private val RESTAURANT_NAME = "restaurant_NAME"
        private val RESTAURANT_LOCATION = "restaurant_LOCATION"
        private val VIDEO_URL = "video_URL"
        private val VIDEO_ID = "video_ID"
        private val USER_NAME = "user_NAME"

        private lateinit var restaurantName: TextView
        private lateinit var vlogerName: TextView
        private lateinit var locationName: TextView
        private lateinit var videoThubmnail: YouTubeThumbnailView
        private lateinit var video_ID: String

        init {
            Log.i("Adapter inner","Called")

        }

        fun bind(vlog: ParseObject) {
            restaurantName = itemView.findViewById(R.id.restaurantName)
            vlogerName = itemView.findViewById(R.id.vlogerName)
            locationName = itemView.findViewById(R.id.locationName)
            videoThubmnail = itemView.findViewById(R.id.videoThumbnail)

            restaurantName.text = vlog.getString(RESTAURANT_NAME)
            vlogerName.text = vlog.getString(USER_NAME)
            videoThubmnail.initialize(R.string.youtube_data_api_key_v3.toString(), this)
            locationName.text = vlog.getString(RESTAURANT_LOCATION)
            video_ID = vlog.getString(VIDEO_ID).toString()
        }

        override fun onInitializationSuccess(
            p0: YouTubeThumbnailView?,
            p1: YouTubeThumbnailLoader?
        ) {
            p1?.setVideo(video_ID)
        }

        override fun onInitializationFailure(
            p0: YouTubeThumbnailView?,
            p1: YouTubeInitializationResult?
        ) {
            Log.i("Youtube Error ", p1.toString())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_vlog_item,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
         return vlogs.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(vlogs.get(position))
    }


}