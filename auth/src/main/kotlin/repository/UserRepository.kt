package dev.ethy.api.auth.repository

import dev.ethy.api.auth.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

	fun findByUsername(username: String): User?

	fun deleteByUsername(username: String)

	fun existsByUsername(username: String): Boolean

	fun existsByUsernameNot(username: String): Boolean
}