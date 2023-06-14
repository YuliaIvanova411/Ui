package ru.netology.nmedia.dto

class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Long = 0,
    var likedByMe: Boolean = false,
    var share: Long = 0,
    )
