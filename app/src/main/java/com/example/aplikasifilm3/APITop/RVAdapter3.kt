package com.example.aplikasifilm3.APITop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasifilm3.R

class RVAdapter3 (
    private val context: Context,
    private val dataList: MutableList<Movie3>
) : RecyclerView.Adapter<RVAdapter3.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster3: ImageView = view.findViewById(R.id.foto3)
        val tvTitle3: TextView = view.findViewById(R.id.judul3)
        val tvReleaseDate3: TextView = view.findViewById(R.id.tanggal3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_top, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = dataList[position]
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .into(holder.ivPoster3)
        holder.tvTitle3.text = movie.title
        holder.tvReleaseDate3.text = movie.release_date
        holder.itemView.setOnClickListener {
            // Handle item click here if needed
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: List<Movie3>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}