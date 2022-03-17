package com.example.animeapp.model.search

data class SearchedAnime(
    val API_DEPRECATION: Boolean,
    val API_DEPRECATION_DATE: String,
    val API_DEPRECATION_INFO: String,
    val last_page: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val results: List<Resulty>
)