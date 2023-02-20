package dev.ethy.api.auth.config

import dev.ethy.api.common.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class JwtAuthenticationFilter(
	val jwtService: JwtService,
	val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val authHeader: String = request.getHeader("Authorization") ?: ""

		if( !authHeader.startsWith("Bearer") ) {
			filterChain.doFilter(request, response)
			return
		}

		val jwt: String = authHeader.substring(7)
		val username: String = jwtService.extractUsername(jwt) ?: return

		if( SecurityContextHolder.getContext().authentication == null ) {
			val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

			if( jwtService.isValid(jwt, userDetails) ) {
				val authToken = UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.authorities
				)

				authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
				SecurityContextHolder.getContext().authentication = authToken
			}
		}

		filterChain.doFilter(request, response)
	}
}