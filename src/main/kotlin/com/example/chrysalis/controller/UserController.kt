package com.example.chrysalis.controller

import com.example.chrysalis.model.User
import com.example.chrysalis.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/add")
    fun addUser(@RequestBody userRequest: Map<String, String>): ResponseEntity<User> {
        val username = userRequest["username"] ?: return ResponseEntity.badRequest().build()
        val password = userRequest["password"] ?: return ResponseEntity.badRequest().build()

        // Check if user already exists
        if (userService.findByUsername(username) != null) {
            return ResponseEntity.status(409).build() // Conflict if the user already exists
        }

        // Add the user
        val newUser = userService.addUser(username, password)
        return ResponseEntity.ok(newUser)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id : Long) : ResponseEntity<User> {
        val user = userService.findById(id) ?: return ResponseEntity.notFound().build()

        userService.deleteUser(id)
        return ResponseEntity.noContent().build()

    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }
}
