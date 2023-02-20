package dev.ethy.api.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["dev.ethy.api"])
class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}