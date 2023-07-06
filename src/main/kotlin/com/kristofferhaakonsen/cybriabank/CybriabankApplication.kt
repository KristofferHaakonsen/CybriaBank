package com.kristofferhaakonsen.cybriabank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CybriabankApplication

fun main(args: Array<String>) {
	runApplication<CybriabankApplication>(*args)
}
