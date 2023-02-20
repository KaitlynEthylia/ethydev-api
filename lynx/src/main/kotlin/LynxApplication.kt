package dev.ethy.api.lynx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LynxApplication

fun main(args: Array<String>) {
	runApplication<LynxApplication>(*args)
}