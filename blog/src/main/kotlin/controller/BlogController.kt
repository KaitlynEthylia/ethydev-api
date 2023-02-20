package dev.ethy.api.blog.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("blog")
class BlogController {

	@GetMapping
	fun getBlogs() {
		TODO()
	}

	@PostMapping
	fun postBlog() {
		TODO()
	}

	@GetMapping("/{userId}")
	fun getBlogsFrom(@PathVariable userId: String) {
		TODO()
	}

}