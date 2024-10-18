package com.example.chrysalis.repository

import com.example.chrysalis.model.Prompt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromptRepository : JpaRepository<Prompt, Long> {
    fun findTopByOrderByIdDesc(): Prompt?
}
