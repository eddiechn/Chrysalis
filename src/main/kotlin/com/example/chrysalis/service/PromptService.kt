package com.example.chrysalis.service

import com.example.chrysalis.model.Prompt
import com.example.chrysalis.repository.PromptRepository
import org.springframework.stereotype.Service

@Service
class PromptService(
    private val promptRepository: PromptRepository
) {

    fun getLatestPrompt(): Prompt? {
        return promptRepository.findTopByOrderByIdDesc()
    }

    fun deleteLatest() {
        val prompt = promptRepository.findTopByOrderByIdDesc()
        if (prompt != null) {
            promptRepository.delete(prompt)

        } else throw IllegalArgumentException("Prompt list is already empty")
    }

    fun addPrompt(content: String): Prompt {
        val prompt = Prompt(content = content)
        return promptRepository.save(prompt)
    }

    fun getAllPrompts() : List<Prompt> {
        return promptRepository.findAll()
    }



}
