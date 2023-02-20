package dev.ethy.api.blog.title

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("blog/{userId}/{title}")
class TitleController {

    @GetMapping
    fun getBlog(
        @PathVariable userId: String,
        @PathVariable title: String,
    ) {
        TODO()
    }

    @PatchMapping
    fun editBlog(
        @PathVariable userId: String,
        @PathVariable title: String,
    ) {
        TODO()
    }

    @DeleteMapping
    fun deleteBlog(
        @PathVariable userId: String,
        @PathVariable title: String,
    ) {
        TODO()
    }
}