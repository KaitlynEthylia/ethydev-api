package dev.ethy.api.auth.payload

data class RegisterRequest(
	val username: String,
	val password: String,
) {
	fun isNotValid() =
		username.length !in 3..64
			|| password.length !in 8..64
}
