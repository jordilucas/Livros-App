package com.jordilucas.livros.model

import android.media.Rating

data class Book(
    var id: String="",
    var title:String="",
    var author:String="",
    var coverUrl:String="",
    var pages:Int=0,
    var year:Int=0,
    var publisher:Publisher?=null,
    var available:Boolean=false,
    var mediaType:MediaType = MediaType.PAPER,
    var rating:Float=0.0f
)