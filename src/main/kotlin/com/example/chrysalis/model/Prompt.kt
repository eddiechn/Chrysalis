package com.example.chrysalis.model

import jakarta.persistence.*
import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "prompts")
data class Prompt(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    @OneToMany(mappedBy = "prompt", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference
    val answers: List<Answer> = emptyList(),

    @Column(nullable = false)
    val date: LocalDate = LocalDate.now()
)
