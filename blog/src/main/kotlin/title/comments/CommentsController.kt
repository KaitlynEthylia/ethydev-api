package dev.ethy.api.blog.title.comments

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("blog/{userId}/{title}/comments")
class CommentsController {

    @GetMapping
    fun getComments(
        @PathVariable userId: String,
        @PathVariable title: String,
    ) {
        TODO()
    }

    @PostMapping
    fun postComment(
        @PathVariable userId: String,
        @PathVariable title: String,
    ) {
        TODO()
    }
}