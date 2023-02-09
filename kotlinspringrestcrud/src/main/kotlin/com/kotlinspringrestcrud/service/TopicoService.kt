package com.kotlinspringrestcrud.service

import com.kotlinspringrestcrud.entity.Topico
import com.kotlinspringrestcrud.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(val topicoRepository: TopicoRepository) {

    fun save(topico: Topico) {
        topicoRepository.save(topico)
    }

    fun find(id: Long): Optional<Topico> {
        return topicoRepository.findById(id)
    }

    fun delete(id: Long) {
        topicoRepository.deleteById(id)
    }

    fun update(topico: Topico) {
        topicoRepository.save(topico)
    }


}