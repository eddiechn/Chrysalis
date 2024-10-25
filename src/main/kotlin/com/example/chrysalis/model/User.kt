package com.example.chrysalis.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "users")

data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val password: String,




    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference
    val answers: List<Answer> = emptyList()
)