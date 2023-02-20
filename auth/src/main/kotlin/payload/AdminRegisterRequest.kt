package dev.ethy.api.auth.payload

data class AdminRegisterRequest(
	val username: String,
	val password: String,
	val adminKey: String,
)
