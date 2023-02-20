package dev.ethy.api.lynx.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("lynx")
class LynxController {

    @PostMapping
    fun postLink() {
        TODO()
    }
}