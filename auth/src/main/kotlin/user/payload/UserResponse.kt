package dev.ethy.api.auth.user.payload

data class UserResponse(
	val username: String,
	val avatar: String?,
	val posts: Int,
	val comments: Int,
	val authorised: Boolean,
)
