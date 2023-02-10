package com.kotlinspringrestcrud.controller

import com.kotlinspringrestcrud.dto.TopicoDTO
import com.kotlinspringrestcrud.entity.Topico
import com.kotlinspringrestcrud.service.TopicoService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("topico")
class CrudController(
    val topicoService: TopicoService
) {

    val logger = KotlinLogging.logger {}

    @GetMapping("test")
    fun test(): ResponseEntity<String> {
        logger.info { "This is info log" }
        return ResponseEntity("Test ok!", HttpStatus.OK)
    }

    @PostMapping("novo")
    fun criar(@RequestBody topico: TopicoDTO): ResponseEntity<String> {
        logger.info { "Inserindo um novo tópico" }
        try {
            val topico = Topico(topico.titulo!!, topico.texto!!, LocalDateTime.now())
            topicoService.save(topico)
        } catch (e: Exception) {
            logger.error { e.message }
            return ResponseEntity("Erro ao criar novo tópico - ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity("Criado!", HttpStatus.CREATED)
    }

    @GetMapping("buscar")
    fun buscar(@RequestParam(required = true) id: Long): ResponseEntity<TopicoDTO> {
        logger.info { "Buscando um tópico por ID" }
        try {
            val topico = topicoService.find(id)
            if (topico.isPresent) {
                val dto =
                    TopicoDTO(topico.get().id, topico.get().titulo, topico.get().texto, topico.get().dt_publicacao)
                return ResponseEntity(dto, HttpStatus.FOUND)
            }
        } catch (e: Exception) {
            logger.error { e.message }
        }
        return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("deletar")
    fun deletar(@RequestParam(required = true) id: Long): ResponseEntity<String> {
        logger.info { "Deletando um tópico por ID" }
        try {
            val topico = topicoService.find(id)
            if (topico.isPresent) {
                topicoService.delete(id)
                return ResponseEntity("Tópico de id = $id deletado.", HttpStatus.OK)
            }
        } catch (e: Exception) {
            logger.error { e.message }
        }
        return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @PutMapping("alterar")
    fun alterar(
        @RequestParam(required = true) id: Long,
        @RequestBody topicoDto: TopicoDTO
    ): ResponseEntity<String> {
        logger.info { "Alterando um tópico por ID" }
        try {
            val topicoOpt = topicoService.find(id)
            if (topicoOpt.isPresent) {
                var topico = topicoOpt.get()
                if (null != topicoDto.titulo && topicoDto.titulo.isNotBlank())
                    topico.titulo = topicoDto.titulo
                if (null != topicoDto.texto && topicoDto.texto.isNotBlank())
                    topico.texto = topicoDto.texto
                topico.dt_publicacao = LocalDateTime.now()
                topicoService.update(topico)
                return ResponseEntity("Tópico de id = $id alterado.", HttpStatus.OK)
            }
        } catch (e: Exception) {
            logger.error { e.message }
        }
        return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

}