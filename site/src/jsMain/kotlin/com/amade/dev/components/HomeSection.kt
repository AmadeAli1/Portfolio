package com.amade.dev.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.varabyte.kobweb.core.DefaultStyleSheet.style
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.dom.*

@Composable
fun HomeSection(theme: Color = Theme) {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier.id("Home")
            .fillMaxWidth()
            .styleModifier {
                property("background-color", theme.backgroundColor1)
                fontFamily("Skranji")
                if (breakpoint <= Breakpoint.SM) {
                    paddingLeft(0.px)
                    paddingRight(0.px)
                    minHeight(350.px)
                } else {
                    height(500.px)
                    paddingLeft(6.cssRem)
                    paddingRight(6.cssRem)
                }
                ".my-name" style {
                    color(theme.secondaryColor)
                    when (breakpoint) {
                        Breakpoint.ZERO -> fontSize(40.px)
                        Breakpoint.SM -> fontSize(40.px)
                        Breakpoint.MD -> fontSize(80.px)
                        Breakpoint.LG -> fontSize(80.px)
                        Breakpoint.XL -> fontSize(80.px)
                    }
                }
                ".my-skill" style {
                    padding(0.px)
                    if (breakpoint <= Breakpoint.SM) {
                        padding(0.px)
                        fontSize(25.px)
                    } else {
                        fontSize(30.px)
                    }
                }
                ".my-skill-content" style {
                    fontWeight(200)
                    fontFamily("Arial")
                    if (breakpoint <= Breakpoint.SM) {
                        maxWidth(90.percent)
                        fontSize(FontSize.Medium)
                    } else {
                        maxWidth(50.percent)
                    }
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO enable responsive text size
        Span(
            attrs = Modifier.classNames("my-name").toAttrs()
        ) {
            Text("AMADE ALY")
        }

        Span(
            attrs = Modifier
                .classNames("my-skill")
                .toAttrs()
        ) {
            Text("BACKEND DEVELOPER")
        }

        P(
            attrs = Modifier
                .classNames("my-skill-content")
                .toAttrs()
        ) {
            Text(
                value = "I am responsible for the development, optimization, and maintenance of the server-side components that power web and mobile applications. With a strong foundation in programming languages, databases, and server technologies, I create robust, scalable, and high-performance systems that enable efficient data processing, storage, and retrieval."
            )
        }

    }

}

@Composable
fun WhatIDoSection(theme: Color = Theme) {
    val breakpoint = rememberBreakpoint()

    val gap by remember {
        derivedStateOf {
            when (breakpoint) {
                Breakpoint.ZERO -> 8.px
                Breakpoint.SM -> 16.px
                Breakpoint.MD -> 24.px
                Breakpoint.LG -> 32.px
                Breakpoint.XL -> 8.px
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .styleModifier {
                property("background-color", theme.backgroundColor2)
                fontFamily("Skranji")
                paddingBottom(64.px)
            },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {

            H1(attrs = Modifier.padding(topBottom = 64.px).toAttrs()) {
                Text("What I Do")
            }

            SimpleGrid(
                numColumns(base = 1, 1, 1, 4, 4),
                modifier = Modifier.margin(bottom = 64.px).gap(gap),
            ) {
                HomeSection.items.forEach { item ->
                    CardItem(item = item)
                }
            }
        }

    }
}

val whatIDoCardStyle by ComponentStyle {
    val theme: Color = Theme
    base {
        Modifier.styleModifier {
            //width(300.px)
            minHeight(350.px)
            padding(24.px)
            borderRadius(5.px)
            color(theme.onCardColor)
            boxShadow("0 1px 1px 0")
            fontFamily("Arial")
            cursor(Cursor.Pointer)
            property("background-color", theme.cardColor)
        }
    }

    Breakpoint.ZERO {
        Modifier.width(270.px)
    }

    Breakpoint.SM {
        Modifier.width(270.px)
    }
    Breakpoint.MD {
        Modifier.width(260.px)
    }
    Breakpoint.LG {
        Modifier.width(290.px)
    }

    Breakpoint.XL {
        Modifier.width(300.px)
    }

    hover {
        Modifier.styleModifier {
            property("background-color", theme.backgroundColor1)
            boxShadow("0 0 0 0")
            color(theme.primaryColor)
        }
    }
}

@Composable
private fun CardItem(item: WhatIDo) {
    Column(
        modifier = whatIDoCardStyle
            .toModifier(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            src = item.image, modifier = Modifier.size(200.px)
        )
        H2(
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

data class WhatIDo(
    val image: String,
    val name: String,
    val description: String,
)

private object HomeSection {
    val items by lazy {
        listOf(
            WhatIDo(
                image = "/me/web.svg",
                name = "Web Development",
                description = "Web development is the process of creating and maintaining websites or web applications that are accessible over the internet or an intranet. It encompasses a wide range of tasks, technologies, and skills aimed at building interactive and functional online platforms."
            ),
            WhatIDo(
                image = "/me/android.svg",
                name = "Android Development",
                description = "Android development refers to the process of creating mobile applications for devices that run on the Android operating system. Android is the most widely used mobile platform globally, powering billions of smartphones, tablets, and other devices."
            ),
            WhatIDo(
                image = "/me/backend.svg",
                name = "Backend Development",
                description = "Backend development, often referred to as server-side development, is a crucial aspect of web and software application development. It involves building and maintaining the server, database, and application logic that power the functionality of a web or mobile application."
            ),
            WhatIDo(
                image = "/me/data-structure.svg",
                name = "Data Structure",
                description = "Data structures are fundamental constructs used in computer science and software development to organize, store, and manage data efficiently. They provide a way to structure and manipulate data to perform various operations such as insertion, deletion, searching, and sorting."
            ),
        )
    }
}