package com.ciscodeto.application.character.delete

interface DeleteCharacter {
    fun delete(id: String) : ResponseModel

    data class ResponseModel(
        val id: String,
        val name: String,
    )
}