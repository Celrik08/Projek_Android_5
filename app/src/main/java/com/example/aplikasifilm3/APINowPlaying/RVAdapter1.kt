package com.example.aplikasifilm3.APINowPlaying

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasifilm3.R

class RVAdapter1 (
    private val context: Context,
    private val dataList: MutableList<Movie1>
) : RecyclerView.Adapter<RVAdapter1.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster1: ImageView = view.findViewById(R.id.foto1)
        val tvTitle1: TextView = view.findViewById(R.id.judul1)
        val tvReleaseDate1: TextView = view.findViewById(R.id.tanggal1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.items_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = dataList[position]
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .into(holder.ivPoster1)
        holder.tvTitle1.text = movie.title
        holder.tvReleaseDate1.text = movie.release_date
        holder.itemView.setOnClickListener {
            // Handle item click here if needed
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: List<Movie1>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}