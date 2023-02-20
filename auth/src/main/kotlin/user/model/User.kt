package dev.ethy.api.auth.user.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "_user")
class User(

	@Id
	@GeneratedValue
	val id: Int?,

	@Enumerated(EnumType.STRING)
	val role: UserRole,

	@Column(
		columnDefinition = "varchar(64)",
		unique = true,
	)
	private val username: String,

	@Column(columnDefinition = "varchar(64)")
	private val password: String,

	@Column
	var avatar: String = "",

	) : UserDetails {

	override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
		mutableListOf(SimpleGrantedAuthority(role.name))

	override fun getPassword(): String = password

	override fun getUsername(): String = username

	override fun isAccountNonExpired(): Boolean = true

	override fun isAccountNonLocked(): Boolean = true

	override fun isCredentialsNonExpired(): Boolean = true

	override fun isEnabled(): Boolean = true
}
