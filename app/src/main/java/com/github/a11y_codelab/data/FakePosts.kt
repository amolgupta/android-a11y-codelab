package com.github.a11y_codelab.data

import com.github.a11y_codelab.model.Post

fun getPosts(): List<Post>  = listOf(
    Post(
        id = "123",
        username = "Haroldo",
        title = "post title",
        content = "this is a post"
    ),
    Post(
        id = "345",
        username = "Channa",
        title = "post title",
        content = "this is a post"
    )
)