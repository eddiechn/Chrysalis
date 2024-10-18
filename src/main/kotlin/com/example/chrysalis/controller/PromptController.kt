package com.example.chrysalis.controller

import com.example.chrysalis.model.Prompt
import com.example.chrysalis.service.PromptService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/prompts")
class PromptController(
    private val promptService: PromptService
) {

    @GetMapping("/latest")
    fun getLatestPrompt(): ResponseEntity<Prompt> {
        val prompt = promptService.getLatestPrompt()
        return if (prompt != null) {
            ResponseEntity.ok(prompt)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/add")
    fun addPrompt(@RequestBody promptRequest: Map<String, String>): ResponseEntity<Prompt> {
        val content = promptRequest["content"] ?: return ResponseEntity.badRequest().build()
        val prompt = promptService.addPrompt(content)
        return ResponseEntity.ok(prompt)
    }

    @GetMapping
    fun getAllPrompts() : ResponseEntity<List<Prompt>> {
        val prompts = promptService.getAllPrompts()
        return ResponseEntity.ok(prompts)
    }

    @DeleteMapping("/latest")
    fun deleteLatest() : ResponseEntity<Void> {
        val prompt = promptService.getLatestPrompt() ?: return ResponseEntity.notFound().build()

        promptService.deleteLatest()
        return ResponseEntity.noContent().build()
    }

}
