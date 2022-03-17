package com.example.animeapp.model.top

import com.example.animeapp.model.search.Resulty

data class TopAnime(
    val API_DEPRECATION: Boolean,
    val API_DEPRECATION_DATE: String,
    val API_DEPRECATION_INFO: String,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val top: List<Resulty>
)