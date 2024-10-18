package com.example.chrysalis.service

import com.example.chrysalis.model.User
import com.example.chrysalis.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findById(id : Long) : User?{
        return userRepository.findById(id).orElse(null)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun addUser(username: String, password: String): User {
        val user = User(username = username, password = password)
        return userRepository.save(user)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun deleteUser(id : Long) {
        val user = userRepository.findById(id)
        if (user.isPresent) {
            userRepository.delete(user.get())

        } else throw IllegalArgumentException("User with id $id not found")

    }
}
