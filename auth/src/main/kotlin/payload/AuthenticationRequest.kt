package dev.ethy.api.auth.payload

data class AuthenticationRequest(
	val username: String,
	val password: String,
)