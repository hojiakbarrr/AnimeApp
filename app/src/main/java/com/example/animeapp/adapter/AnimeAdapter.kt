package com.example.animeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.R
import com.example.animeapp.anime_detail.AnimeDetailsBottomSheet
import com.example.animeapp.model.search.Resulty
import com.squareup.picasso.Picasso

class AnimeAdapter(
    private val parentActivity: AppCompatActivity,
    private val animes: List<Resulty>,
) : RecyclerView.Adapter<AnimeAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val anime = animes[position]
        val view = holder.itemView

        val name = view.findViewById<TextView>(R.id.name)
        val image = view.findViewById<ImageView>(R.id.image)

        name.text = anime.title
        Picasso.get().load(anime.image_url).into(image)

        view.setOnClickListener {
            AnimeDetailsBottomSheet(anime).apply {
                show(parentActivity.supportFragmentManager, "AnimeDetailsBottomSheet")
            }
        }
    }


    override fun getItemCount(): Int {
        return animes.size
    }


}