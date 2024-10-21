package com.example.chrysalis

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChrysalisApplication

fun main(args: Array<String>) {
	// Load dotenv environment variables into system properties
	val dotenv = dotenv()
	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	// Start Spring Boot application
	runApplication<ChrysalisApplication>(*args)
}
