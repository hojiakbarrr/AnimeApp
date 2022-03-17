package com.example.animeapp.anime_detail

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.animeapp.databinding.AnimeDetailsBottomSheetLayoutBinding
import com.example.animeapp.model.search.Resulty
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class AnimeDetailsBottomSheet (
    private val anime: Resulty
) : BottomSheetDialogFragment() {

    lateinit var binding: AnimeDetailsBottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val animeInflater = LayoutInflater.from(requireContext())
        binding = AnimeDetailsBottomSheetLayoutBinding.inflate(animeInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Picasso.get().load(anime.image_url).into(image)
            name.text = anime.title

            rating.text = anime.score.toString()
            pgRating.text = anime.rated
            episodes.text = "${anime.episodes} episodes"
            synopsis.text = anime.synopsis
            dates.text = if (anime.end_date .isEmpty()) {
                "${formatDate(anime.start_date)} - ongoing"
            } else {
                "${formatDate(anime.start_date)} - ${formatDate(anime.end_date)}"
            }

            binding.knowMoreText.setOnClickListener {
                openCustomTab(activity as AppCompatActivity, Uri.parse(anime.url))
            }


        }
    }

    private fun openCustomTab(activity: AppCompatActivity, url: Uri) {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.build().launchUrl(activity, url)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        return if (date.contains("-")) {
            val newDate = date.substring(0, date.lastIndexOf("-"))
            val _date = SimpleDateFormat("yyyy-MM").parse(newDate)
            SimpleDateFormat("MMM yyyy").format(_date)
        } else {
            date
        }
    }

}