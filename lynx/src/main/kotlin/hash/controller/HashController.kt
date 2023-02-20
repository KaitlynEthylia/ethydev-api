package dev.ethy.api.lynx.hash.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("lynx/{hash}")
class HashController {

    @GetMapping
    fun getLink(@PathVariable hash: String) {
        TODO()
    }

    @PatchMapping
    fun editLink(@PathVariable hash: String) {
        TODO()
    }

    @DeleteMapping
    fun deleteLink(@PathVariable hash: String) {
        TODO()
    }
}