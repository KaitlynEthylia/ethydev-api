package dev.ethy.api.common.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap

@Service
class JwtService(
	@Value("\${secret-key}") val SECRET_KEY: String
) {

	private fun getSigningKey(): Key {
		val bytes: ByteArray = Decoders.BASE64.decode(SECRET_KEY)
		return Keys.hmacShaKeyFor(bytes)
	}

	fun extractAllClaims(token: String): Claims =
		Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.body

	fun <T> extractClaim(token: String, resolver: Function<Claims, T>): T {
		val claims: Claims = extractAllClaims(token)
		return resolver.apply(claims)
	}

	fun extractUsername(token: String): String? =
		extractClaim(token, Claims::getSubject)

	fun extractExpiration(token: String): Date? =
		extractClaim(token, Claims::getExpiration)

	fun generateToken(
		userDetails: UserDetails,
		extraClaims: Map<String, Any> = HashMap(),
	): String =
		Jwts.builder()
			.setClaims(extraClaims)
			.setSubject(userDetails.username)
			.setIssuedAt(
				Date())
			.setExpiration(
				Date(System.currentTimeMillis()
					+ 1000 * 60 * 60 * 48)) //Expire 48 hours after issuing
			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
			.compact()

	fun isValid(token: String, userDetails: UserDetails): Boolean =
		extractUsername(token) == userDetails.username
			&& !isTokenExpired(token)

	private fun isTokenExpired(token: String): Boolean =
		extractExpiration(token)?.before(Date()) ?: true

}
