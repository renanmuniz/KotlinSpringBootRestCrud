package com.kotlinspringrestcrud.dto

import java.time.LocalDateTime

data class TopicoDTO(
    val id: Long?,
    val titulo: String?,
    val texto: String?,
    val dtPublicacao: LocalDateTime?
)
