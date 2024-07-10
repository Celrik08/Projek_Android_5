package com.example.aplikasifilm3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikasifilm3.APINowPlaying.ApiClient1
import com.example.aplikasifilm3.APINowPlaying.Movie1
import com.example.aplikasifilm3.APINowPlaying.NowPlayingResponse1
import com.example.aplikasifilm3.APINowPlaying.RVAdapter1
import com.example.aplikasifilm3.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: RVAdapter1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RVAdapter1(requireContext(), mutableListOf()) // Mengubah listOf() menjadi mutableListOf()

        binding.rvMain1.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMain1.adapter = adapter

        remoteGetNowPlayingMovies()
    }

    private fun remoteGetNowPlayingMovies() {
        ApiClient1.apiService.getNowPlayingMovies().enqueue(object : Callback<NowPlayingResponse1> {
            override fun onResponse(call: Call<NowPlayingResponse1>, response: Response<NowPlayingResponse1>) {
                if (response.isSuccessful) {
                    val nowPlayingResponse = response.body()
                    val movies = nowPlayingResponse?.results
                    if (movies != null) {
                        setDataToAdapter(movies)
                    } else{
                        Log.e("Error", "Response body is null or empty.")
                    }
                } else {
                    Log.e("Error", "Failed to fetch data: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NowPlayingResponse1>, t: Throwable) {
                Log.e("Error", "Failed to fetch data: ${t.message}")
            }
        })
    }

    private  fun setDataToAdapter(movies: List<Movie1>) {
        adapter.setData(movies)
    }
}