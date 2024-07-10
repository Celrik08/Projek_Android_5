package com.example.aplikasifilm3.APIPopular

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasifilm3.R

class RVAdapter2 (
    private val context: Context,
    private val dataList: MutableList<Movie2>
) : RecyclerView.Adapter<RVAdapter2.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster2: ImageView = view.findViewById(R.id.foto2)
        val tvTitle2: TextView = view.findViewById(R.id.judul2)
        val tvReleaseDate2: TextView = view.findViewById(R.id.tanggal2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_popular, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = dataList[position]
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .into(holder.ivPoster2)
        holder.tvTitle2.text = movie.title
        holder.tvReleaseDate2.text = movie.release_date
        holder.itemView.setOnClickListener {
            // Handle item click here if needed
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: List<Movie2>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}