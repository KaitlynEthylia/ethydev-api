package dev.ethy.api.blog.title.comments.comment

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("blog/{userId}/{title}/comments/{commentId}")
class CommentController {

    @GetMapping
    fun getComment(
        @PathVariable userId: String,
        @PathVariable title: String,
        @PathVariable commentId: String,
    ) {
        TODO()
    }

    @PostMapping
    fun postComment(
        @PathVariable userId: String,
        @PathVariable title: String,
        @PathVariable commentId: String,
    ) {
        TODO()
    }

    @PatchMapping
    fun editComment(
        @PathVariable userId: String,
        @PathVariable title: String,
        @PathVariable commentId: String,
    ) {
        TODO()
    }

    @DeleteMapping
    fun deleteComment(
        @PathVariable userId: String,
        @PathVariable title: String,
        @PathVariable commentId: String,
    ) {
        TODO()
    }

}