package com.kristofferhaakonsen.cybriabank

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CybriabankApplication

fun main(args: Array<String>) {
    SpringApplication.run(CybriabankApplication::class.java, *args)
}
