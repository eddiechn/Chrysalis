package com.example.chrysalis.controller

import com.example.chrysalis.model.Answer
import com.example.chrysalis.model.User
import com.example.chrysalis.service.AnswerService
import com.example.chrysalis.service.PromptService
import com.example.chrysalis.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/answers")
class AnswerController(
    private val answerService: AnswerService,
    private val promptService: PromptService,
    private val userService: UserService
) {

    @PostMapping("/add")
    fun addAnswer(
        @RequestBody answerRequest: Map<String, String>
    ): ResponseEntity<Answer> {
        val content = answerRequest["content"] ?: return ResponseEntity.badRequest().build()
        val userId = answerRequest["userId"]?.toLongOrNull() ?: return ResponseEntity.badRequest().build() // Expecting userId in request body
        val user: User = userService.findById(userId) ?: return ResponseEntity.notFound().build()
        val prompt = promptService.getLatestPrompt() ?: return ResponseEntity.notFound().build()
        val answer = answerService.addAnswer(content, user, prompt)
        return ResponseEntity.ok(answer)
    }

    @GetMapping("/my")
    fun getUserAnswers(@RequestParam userId: Long): ResponseEntity<List<Answer>> {
        val user: User = userService.findById(userId) ?: return ResponseEntity.notFound().build() // Use findById instead of findByUsername
        val answers = answerService.getUserAnswers(user)
        return ResponseEntity.ok(answers)
    }

    @DeleteMapping("/latest")
    fun deleteLatest(@RequestParam userId: Long) : ResponseEntity<Void> {
        val user : User = userService.findById(userId) ?: return ResponseEntity.notFound().build()
        val answer = answerService.getLatestAnswer(user) ?: return ResponseEntity.notFound().build()

        answerService.deleteLatest(answer)
        return ResponseEntity.noContent().build()
    }


}
