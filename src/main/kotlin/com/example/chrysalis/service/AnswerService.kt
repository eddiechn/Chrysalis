package com.example.chrysalis.service

import com.example.chrysalis.model.Answer
import com.example.chrysalis.model.Prompt
import com.example.chrysalis.model.User
import com.example.chrysalis.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository
) {

    fun addAnswer(content: String, user: User, prompt: Prompt): Answer {
        val answer = Answer(content = content, user = user, prompt = prompt)
        return answerRepository.save(answer)
    }

    fun getUserAnswers(user: User): List<Answer> {
        return answerRepository.findAllByUser(user)
    }

    fun getLatestAnswer(): Answer? {
        return answerRepository.findTopByOrderByIdDesc()
    }

    fun deleteLatest() {
        val answer = answerRepository.findTopByOrderByIdDesc()
        if (answer != null) {
            answerRepository.delete(answer)

        } else throw IllegalArgumentException("Answer list is already empty")
    }
}
