package dev.ethy.api.auth.controller

import dev.ethy.api.auth.payload.AdminRegisterRequest
import dev.ethy.api.auth.payload.AuthenticationRequest
import dev.ethy.api.auth.payload.AuthenticationResponse
import dev.ethy.api.auth.payload.RegisterRequest
import dev.ethy.api.auth.service.AuthenticationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
	val authService: AuthenticationService,
	@Value("\${admin-key}") val adminKey: String,
) {

	@GetMapping
	fun login(
		@RequestBody request: AuthenticationRequest
	): ResponseEntity<AuthenticationResponse> =
		ResponseEntity.ok(
			authService.authenticate(request))

	@PostMapping
	fun register(
		@RequestBody request: RegisterRequest
	): ResponseEntity<AuthenticationResponse> {
		if( request.isNotValid() ) throw Exception("")

		return ResponseEntity.ok(
			authService.register(request)
		)
	}

	@PostMapping("/admin")
	fun adminRegister(
		@RequestBody request: AdminRegisterRequest
	): ResponseEntity<AuthenticationResponse> =
		if( request.adminKey != adminKey ) throw Exception("")
		else ResponseEntity.ok(
			authService.register(
				RegisterRequest(
				request.username,
				request.password,
			), asAdmin = true)
		)
}