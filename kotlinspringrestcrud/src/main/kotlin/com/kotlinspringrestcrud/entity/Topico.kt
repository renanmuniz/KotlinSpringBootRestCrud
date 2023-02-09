package com.kotlinspringrestcrud.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "topico")
class Topico {

    constructor(titulo: String, texto: String, dt_publicacao: LocalDateTime) {
        this.titulo = titulo
        this.texto = texto
        this.dt_publicacao = dt_publicacao
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0L

    @Column(name = "titulo", nullable = false)
    var titulo: String = ""

    @Column(name = "texto", nullable = false)
    var texto: String = ""

    @Column(name = "dt_publicacao")
    var dt_publicacao: LocalDateTime = LocalDateTime.now()


}