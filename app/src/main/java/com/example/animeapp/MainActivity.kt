package com.example.animeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeapp.adapter.AnimeAdapter
import com.example.animeapp.api.AnimeService
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.model.search.SearchedAnime
import com.example.animeapp.model.top.TopAnime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            val animeService = AnimeService.create()
            val call = animeService.getTopAnimes()

            call.enqueue(object : Callback<TopAnime> {

                override fun onResponse(call: Call<TopAnime>, response: Response<TopAnime>) {
                    if (response.body() != null) {
                        val top = response.body()!!.top
                        animeRecyclerView.adapter = AnimeAdapter(this@MainActivity, top)
                        animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    }
                }

                override fun onFailure(call: Call<TopAnime>, t: Throwable) {

                }

            })
        }


        binding.btnSearch.setOnClickListener {
            val animeService = AnimeService.create()

            val searchedAnime = binding.searchInputEditText.text.toString()
            val callSearchedAnime = animeService.getSearchedAnime(searchedAnime)

            callSearchedAnime.enqueue(object : Callback<SearchedAnime> {

                override fun onResponse(
                    call: Call<SearchedAnime>,
                    response: Response<SearchedAnime>,
                ) {
                    if (response.body() != null) {
                        val searchedAnimes = response.body()!!.results
                        binding.animeRecyclerView.adapter = AnimeAdapter(this@MainActivity, searchedAnimes)
                        binding.animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    }
                }

                override fun onFailure(call: Call<SearchedAnime>, t: Throwable) {

                }

            })
        }
    }
}
