package com.learn.jetmovie.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: Any? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)