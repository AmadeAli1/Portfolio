package com.amade.dev.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.amade.dev.components.*
import com.amade.dev.styles.Theme
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import kotlinx.browser.document

@Page
@Composable
fun HomePage() {
    val theme = remember { Theme }
    LaunchedEffect(theme) {
        document.title = "AlDeveloper"
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .styleModifier { property("background-color", theme.backgroundColor1) }
    ) {
        NavBar(theme)
        HomeSection(theme)
        WhatIDoSection()
        ProjectSection()
        AboutSection()
    }
}