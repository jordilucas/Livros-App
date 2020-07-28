package com.jordilucas.livros.model

data class Publisher(var id: String="", var name:String=""){
    override fun toString(): String = "$id - $name"
}