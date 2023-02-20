package dev.ethy.api.auth.config

import dev.ethy.api.auth.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class AuthConfig(
	val repository: UserRepository
) {

	@Bean
	fun userDetailsService(): UserDetailsService =
		UserDetailsService { username ->
			repository.findByUsername(username) ?:
			throw UsernameNotFoundException("User '${username}' Not Found!")
		}

}