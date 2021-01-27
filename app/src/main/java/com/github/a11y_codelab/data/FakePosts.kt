package com.github.a11y_codelab.data

import com.github.a11y_codelab.R
import com.github.a11y_codelab.model.Post

fun getPosts(): List<Post>  = listOf(
    Post(
        id = "123",
        username = "Haroldo",
        content = "Can't wait for summers, and invite the team for a barbecue",
        image = R.drawable.bbq,
        contentDescription = "An illustration of a barbecue in sunlight"
    ),
    Post(
        id = "91011",
        username = "Morten",
        content = "Let's run an experiment to remove this feature"
    ),
    Post(
        id = "345",
        username = "Channa",
        content = "I am flying back to Sri Lanka after a long time.",
        image = R.drawable.sl,
        contentDescription = "A Map of Sri Lanka"
    ),
    Post(
        id = "678",
        username = "Julien",
        content = "Alexa set a timer for 5 minutes",
        image = R.drawable.alexa,
        contentDescription = "Picture of a smart speaker"
    )
)