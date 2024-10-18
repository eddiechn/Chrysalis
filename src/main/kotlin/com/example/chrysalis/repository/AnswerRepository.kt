package com.example.chrysalis.repository

import com.example.chrysalis.model.Answer
import com.example.chrysalis.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findAllByUser(user: User) : List<Answer>
    fun findTopByUserOrderByIdDesc(user : User): Answer?
}