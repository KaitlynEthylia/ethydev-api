package dev.ethy.api.auth.validation.controller

import dev.ethy.api.auth.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth/validate")
class ValidationController(
	val repository: UserRepository
) {

	@GetMapping("/exists")
	fun userExists(@PathVariable username: String): ResponseEntity<Boolean> =
		ResponseEntity.ok(repository.existsByUsername(username))

	@GetMapping("/available")
	fun usernameAvailable(@PathVariable username: String): ResponseEntity<Boolean> =
		ResponseEntity.ok(repository.existsByUsernameNot(username))
}