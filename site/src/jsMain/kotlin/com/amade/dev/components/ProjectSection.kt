package com.amade.dev.components

import androidx.compose.runtime.*
import com.amade.dev.styles.Color
import com.amade.dev.styles.Theme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun ProjectSection(theme: Color = Theme) {
    val breakpoint = rememberBreakpoint()

    var showMoreProjects by remember { mutableStateOf(false) }

    val itemSize by remember {
        derivedStateOf {
            if (breakpoint <= Breakpoint.SM) {
                3
            } else if (breakpoint <= Breakpoint.MD) {
                3
            } else {
                6
            }
        }
    }

    val projects = remember { mutableStateListOf<Project>() }
    LaunchedEffect(key1 = itemSize, key2 = showMoreProjects) {
        if (!showMoreProjects) {
            ProjectSection.projects.take(itemSize).also {
                projects.clear()
                projects.addAll(it)
            }
        } else {
            ProjectSection.projects.also {
                projects.clear()
                projects.addAll(it)
            }
        }
    }

    Box(
        modifier = Modifier
            .id("Projects")
            .fillMaxWidth()
            .styleModifier {
                property("background-color", theme.backgroundColor1)
                fontFamily("Skranji")
                paddingBottom(64.px)
                if (breakpoint <= Breakpoint.MD) {
                    minHeight(300.px)
                } else {
                    height(100.vh)
                }
                if (showMoreProjects && breakpoint > Breakpoint.MD) {
                    height(Height.MinContent)
                    return@styleModifier
                }
                if (!showMoreProjects && breakpoint == Breakpoint.LG) {
                    height(Height.MinContent)
                } else {
                    minHeight(300.px)
                }
            },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            H1(attrs = Modifier.padding(topBottom = 64.px).toAttrs()) {
                Text("Projects")
            }

            //TODO MD to 2
            SimpleGrid(
                numColumns(base = 1, 1, 1, 3, 3),
                modifier = Modifier.gap(32.px),
            ) {
                projects.forEach { item ->
                    ProjectItem(item)
                }
            }

            if (ProjectSection.projects.size > itemSize) {
                com.varabyte.kobweb.silk.components.forms.Button(
                    onClick = {
                        showMoreProjects = !showMoreProjects
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .margin(top = 32.px),
                    colorScheme = ColorSchemes.Yellow
                ) {
                    Text(if (showMoreProjects) "Show less" else "Show more")
                }
            }

        }

    }

}

val projectCardStyle by ComponentStyle {
    val theme: Color = Theme
    base {
        Modifier.styleModifier {
            minHeight(290.px)
            width(400.px)
            padding(32.px)
            borderRadius(5.px)
            color(theme.onCardColor)
            boxShadow("0 1px 1px 0")
            fontFamily("Arial")
            overflow(Overflow.Hidden)
            cursor(Cursor.Pointer)
            property("background-color", theme.cardColor)
        }
    }

    Breakpoint.MD {
        Modifier.width(300.px)
    }
    Breakpoint.SM {
        Modifier.width(300.px)
    }

    Breakpoint.ZERO {
        Modifier.width(300.px)
    }
    hover {
        Modifier
            .outline(width = 0.3.px, LineStyle.Solid)
            .styleModifier {
                property("background-color", theme.backgroundColor1)
                property("outline-color", theme.secondaryColor)
                boxShadow("0 0 0 0")
                color(theme.primaryColor)
            }
    }
}

@Composable
private fun ProjectItem(item: Project) {
    Column(
        modifier = projectCardStyle
            .toModifier()
            .onClick {
                item.link?.let { url -> window.open(url = url, target = "_blank") }
            },
    ) {
        H4(
            attrs = Modifier
                .margin(top = 10.px)
                .styleModifier {
                    textAlign(TextAlign.Center)
                }
                .toAttrs()
        ) { Text(item.name) }
        Span(attrs = Modifier.margin(top = 8.px).toAttrs()) {
            Text(item.description)
        }
    }
}


data class Project(
    val name: String,
    val description: String,
    val link: String? = null,
)

object ProjectSection {
    val projects by lazy {
        listOf(
            Project(
                name = "Tic Tac Toe",
                description = "Also known as Noughts and Crosses, is a classic two-player paper-and-pencil game often played on a 3x3 grid. The objective of the game is for one player to align their symbols (either \"X\" or \"O\") in a row, column, or diagonal, before the opponent does.Online and offline mode support (Windows).",
                link = "https://github.com/AmadeAli1/TicTacToeGame"
            ),
            Project(
                name = "Authentication JWT API",
                description = "A REST API (Representational State Transfer Application Programming Interface) that uses JWT (JSON Web Tokens) for authentication is a common and secure way to handle user authentication and authorization in web applications.",
                link = "https://github.com/AmadeAli1/AuthenticationServerJWT"
            ),
            Project(
                name = "Database ORM",
                description = "A Database ORM (Object-Relational Mapping) is a programming technique and software tool that facilitates the interaction between a software application and a relational database. It provides a higher-level, object-oriented abstraction over the underlying database.",
                link = "https://github.com/AmadeAli1/DatabaseORM-ORACLE"
            ),
            Project(
                name = "FileReader with Java Annotations",
                description = "A \"File Reader + Java retention\" in Java refers to a mechanism or class that is used to read the contents of a file in a Java program. It allows developers to access and process the data stored in a file,",
                link = "https://github.com/AmadeAli1/FileTextReader"
            ),

            )
    }
}

//, allowing developers to work with objects and classes in their code rather than writing raw SQL queries.