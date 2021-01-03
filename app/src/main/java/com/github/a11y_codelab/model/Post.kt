package com.github.a11y_codelab.model

data class Post(
    val id: String,
    val title: String,
    val content: String,
    val username: String,
    val image: Int? = null,
    val contentDescription: String? = null,
    val commentCount: Int = 0,
    val likeCount: Int = 0
)