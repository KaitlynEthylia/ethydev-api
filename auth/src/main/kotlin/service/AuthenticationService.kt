package dev.ethy.api.auth.service

import dev.ethy.api.auth.payload.AuthenticationRequest
import dev.ethy.api.auth.payload.AuthenticationResponse
import dev.ethy.api.auth.payload.RegisterRequest
import dev.ethy.api.auth.repository.UserRepository
import dev.ethy.api.auth.user.model.User
import dev.ethy.api.auth.user.model.UserRole
import dev.ethy.api.common.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.sound.midi.SysexMessage

@Service
class AuthenticationService(
	val repository: UserRepository,
	val encoder: PasswordEncoder,
	val jwtService: JwtService,
	val authenticationManager: AuthenticationManager,
) {

	fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
		val user = repository.findByUsername(request.username) ?: throw UsernameNotFoundException("User '${request.username}' Does Not Exist! D:")

		authenticationManager.authenticate(
			UsernamePasswordAuthenticationToken(
				request.username,
				request.password + user.dateCreated,
			)
		)

		val jwtToken = jwtService.generateToken(user)
		return AuthenticationResponse(jwtToken)
	}

	fun register(request: RegisterRequest, asAdmin: Boolean = false): AuthenticationResponse {
		if( repository.findByUsername(request.username) != null) {
			throw Exception()
		}

		val time = System.currentTimeMillis()

		val user = User(
			null,
			if (asAdmin) UserRole.ADMIN
			else UserRole.USER,
			request.username,
			encoder.encode(request.password + time),
			dateCreated = time,
		)
		repository.save(user)
		val jwtToken = jwtService.generateToken(user)
		return AuthenticationResponse(jwtToken)
	}
}