package com.amade.dev.components

import androidx.compose.runtime.Composable
import com.amade.dev.styles.Color
import com.amade.dev.styles.Theme
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaFileWord
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun AboutSection(theme: Color = Theme) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .id("About")
            .fillMaxWidth().styleModifier {
                property("background-color", theme.backgroundColor2)
                fontFamily("Skranji")
                paddingBottom(64.px)
                if (breakpoint <= Breakpoint.MD) {
                    minHeight(300.px)
                } else {
                    height(100.vh)
                }
            },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 64.px)
            ) {
                H1(attrs = Modifier.margin(0.px).toAttrs()) {
                    Text("About")
                }
                H1(attrs = Modifier.margin(left = 5.px)
                    .styleModifier { color(theme.secondaryColor) }
                    .toAttrs()
                ) {
                    Text(
                        "me"
                    )
                }
            }

            Column(modifier = Modifier.styleModifier {
                if (breakpoint <= Breakpoint.MD) {
                    val size = 1.5.cssRem
                    paddingLeft(size)
                    paddingRight(size)
                } else {
                    val size = 20.cssRem
                    paddingRight(size)
                    paddingLeft(size)
                }
            }) {
                H1(attrs = Modifier.styleModifier { color(theme.secondaryColor) }.toAttrs()) {
                    Text("Profile")
                }
                P(attrs = Modifier.id("info").toAttrs()) {
                    Text(
                        "Currently a finalist student in the degree course in Engineering Informatics. Throughout my academic career I have acquired knowledge focused on development technologies. Career ambitions include growth opportunities as part of innovative programmes, promotion ofn sustainable development in national and international and capacity-building activities."
                    )
                }

                H1(
                    attrs = Modifier.id("skills")
                        .styleModifier { color(theme.secondaryColor) }.toAttrs()
                ) {
                    Text("Skills")
                }

                SimpleGrid(
                    numColumns = numColumns(base = 1, 1, 2, 3, 3),
                    modifier = Modifier.gap(16.px)
                ) {
                    AboutSection.skills.forEach { skill ->
                        SkillItem(skill = skill)
                    }
                }


                A(href = "/me/cv.pdf") {
                    Button(
                        onClick = {
                        },
                        colorScheme = ColorSchemes.Yellow,
                        modifier = Modifier.margin(bottom = 32.px, top = 24.px)
                    ) {
                        Text("View Resume")
                        FaFileWord(size = IconSize.XL, modifier = Modifier.margin(left = 8.px))
                    }
                }

            }


        }

    }
}

@Composable
fun SkillItem(theme: Color = Theme, skill: Skill) {
    Column {
        P {
            Text(skill.name)
        }
        Box(modifier = Modifier
            .height(10.px)
            .width((skill.experience * 3).px)
            .styleModifier {
                property("background-color", theme.cardColor)
                borderRadius(8.px)
            }
        )
    }
}

data class Skill(
    val name: String,
    val experience: Int,
)

object AboutSection {
    val skills by lazy {
        listOf(
            Skill("Java", 100),
            Skill("Kotlin", 85),
            Skill("Android Development", 70),
            Skill("Backend Development", 70),
            Skill("Spring Boot Framework", 70),
            Skill("Data Structure", 70),
            Skill("Reactive Programming", 50),
            Skill("Rest Api", 70),
            Skill("SQL Databases", 60),
            Skill("Firebase", 50),
            Skill("Web Development", 40),
            Skill("Css", 40),
            Skill("Html", 40),
        )
    }
}