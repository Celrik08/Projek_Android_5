package com.example.aplikasifilm3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikasifilm3.APIPopular.ApiClient2
import com.example.aplikasifilm3.APIPopular.Movie2
import com.example.aplikasifilm3.APIPopular.PopularResponse2
import com.example.aplikasifilm3.APIPopular.RVAdapter2
import com.example.aplikasifilm3.databinding.FragmentPopularBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var adapter: RVAdapter2

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopularFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RVAdapter2(requireContext(), mutableListOf())

        binding.rvMain2.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMain2.adapter = adapter

        remoteGetPopularMovies()
    }

    private fun remoteGetPopularMovies() {
        ApiClient2.apiService.getPopularMovies().enqueue(object : Callback<PopularResponse2> {
            override fun onResponse(call: Call<PopularResponse2>, response: Response<PopularResponse2>) {
                if (response.isSuccessful) {
                    val popularResponse = response.body()
                    val movies = popularResponse?.results
                    if (movies != null) {
                        setDataToAdapter(movies)
                    } else {
                        Log.e("Error", "Response body is null or empty.")
                    }
                }


            }

            override fun onFailure(call: Call<PopularResponse2>, t: Throwable) {
                Log.e("Error", "Failed to fetch data: ${t.message}")
            }
        })
    }
    private fun setDataToAdapter(movies: List<Movie2>) {
        adapter.setData(movies)
    }
}