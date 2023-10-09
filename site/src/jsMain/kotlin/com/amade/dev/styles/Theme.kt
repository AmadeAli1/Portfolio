package com.amade.dev.styles

sealed class Color(
    val primaryColor: String,
    val secondaryColor: String,
    val backgroundColor1: String,
    val backgroundColor2: String,
    val cardColor: String,
    val onCardColor: String,
)

data object Theme : Color(
    primaryColor = "white",
    secondaryColor = "#fcdc4c",
    backgroundColor1 = "#202020",
    backgroundColor2 = "#272727",
    cardColor = "#fcdc4c",
    onCardColor = "black"
)

