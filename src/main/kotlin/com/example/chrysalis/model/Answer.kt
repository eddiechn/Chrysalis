package com.example.chrysalis.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name = "answers")
data class Answer(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prompt_id")
    @JsonBackReference
    val prompt: Prompt
)
