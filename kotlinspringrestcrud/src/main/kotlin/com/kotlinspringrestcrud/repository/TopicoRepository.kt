package com.kotlinspringrestcrud.repository

import com.kotlinspringrestcrud.entity.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository : JpaRepository<Topico, Long>{
}