package dev.ethy.api.auth.user.service

import dev.ethy.api.auth.repository.UserRepository
import dev.ethy.api.auth.user.model.User
import dev.ethy.api.auth.user.model.UserRole
import dev.ethy.api.auth.user.payload.UpdateRequest
import dev.ethy.api.auth.user.payload.UserResponse
import jakarta.transaction.Transactional
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(
	val userRepository: UserRepository
) {

	fun isAuthorised(username: String): Boolean {
		val user = SecurityContextHolder.getContext().authentication.principal
			as User

		return username == user.username
			|| user.authorities.contains(
				SimpleGrantedAuthority(UserRole.ADMIN.name))

	}

	fun getDetails(username: String): UserResponse {
		val user = SecurityContextHolder.getContext().authentication.principal
			as User

		return if( !isAuthorised(username) ) UserResponse(
				user.username,
				user.avatar,
				0,
				0,
				false,
			)
		else UserResponse(
				user.username,
				user.avatar,
				1,
				1,
				true,
			)
	}

	@Transactional
	fun updateDetails(username: String, request: UpdateRequest): UserResponse {
		val user: User = userRepository.findByUsername(username)
			?: return getDetails(username)

		if( request.avatar != null ) {
			user.avatar = request.avatar
		}

		userRepository.save(user)
		return getDetails(username)
	}

	@Transactional
	fun deleteUser(username: String) =
		userRepository.deleteByUsername(username)
}