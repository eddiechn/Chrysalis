package com.example.chrysalis.service

import com.example.chrysalis.model.Answer
import com.example.chrysalis.model.Prompt
import com.example.chrysalis.model.User
import com.example.chrysalis.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val userService : UserService
) {

    fun addAnswer(content: String, user: User, prompt: Prompt): Answer {
        val answer = Answer(id = user.id, content = content, user = user, prompt = prompt)
        return answerRepository.save(answer)
    }

    fun getUserAnswers(user: User): List<Answer> {
        return answerRepository.findAllByUser(user)
    }

    fun getLatestAnswer(user: User): Answer? {
        return answerRepository.findTopByUserOrderByIdDesc(user)
    }

    fun deleteLatest(answer: Answer) {
        answerRepository.delete(answer)

    }
}
