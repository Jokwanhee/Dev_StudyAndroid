package com.goni99.musicplayer

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class MusicAdapter
    :RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    val musicList = mutableListOf<Music>()
    var mediaPlayer: MediaPlayer? = null

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var musicUri: Uri? = null

        init {
            itemView.setOnClickListener {
                if (mediaPlayer != null){
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        val img = itemView.findViewById<ImageView>(R.id.music_image_view)
        val artist = itemView.findViewById<TextView>(R.id.artist_txt_view)
        val title = itemView.findViewById<TextView>(R.id.title_txt_view)
        val duration = itemView.findViewById<TextView>(R.id.duration_txt_view)
        fun bind(music:Music){
            musicUri = music.getMusicUri()
            img.setImageURI(music.getAlbumUri())
            artist.text = music.artist
            title.text = music.title
            val sdf = SimpleDateFormat("mm:ss")
            duration.text = sdf.format(music.duration)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musicList[position])
    }
}