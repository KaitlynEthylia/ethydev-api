package dev.ethy.api.auth.user.controller

import dev.ethy.api.auth.user.payload.UpdateRequest
import dev.ethy.api.auth.user.payload.UserResponse
import dev.ethy.api.auth.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth/@{username}")
class UserController(
	val userService: UserService
) {

	@GetMapping
	fun getDetails(@PathVariable username: String): ResponseEntity<UserResponse> =
		ResponseEntity.ok(userService.getDetails(username))

	@PatchMapping
	fun updateDetails(
		@PathVariable username: String,
		@RequestBody updateRequest: UpdateRequest,
	):ResponseEntity<UserResponse> =
		if( userService.isAuthorised(username) ) {
			ResponseEntity.ok(
				userService.updateDetails(username, updateRequest)
			)
		} else throw Exception("")

	@DeleteMapping
	fun deleteUser(@PathVariable username: String) {
		if( userService.isAuthorised(username) ) {
			userService.deleteUser(username)
		} else throw Exception("")
	}
}