package com.amade.dev.components

import androidx.compose.runtime.Composable
import com.amade.dev.styles.Color
import com.amade.dev.styles.Theme
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.style.KobwebComposeStyleSheet.style
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.InputSize
import com.varabyte.kobweb.silk.components.forms.TextInput
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun ContactSection(theme: Color = Theme) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .id("Contact")
            .fillMaxWidth().styleModifier {
                property("background-color", theme.backgroundColor1)
                fontFamily("Skranji")
                if (breakpoint <= Breakpoint.MD) {
                    height(900.px)
                } else {
                    height(100.vh)
                }
            },
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter)
                .styleModifier {
                    paddingTop(64.px)
                    if (breakpoint <= Breakpoint.MD) {
                        paddingLeft(1.5.cssRem)
                        paddingRight(1.5.cssRem)
                    } else {
//                        paddingTop(64.px)
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                H1(attrs = Modifier.margin(0.px).toAttrs()) {
                    Text("Contact")
                }
                H1(attrs = Modifier.margin(left = 5.px).styleModifier { color(theme.secondaryColor) }.toAttrs()) {
                    Text(
                        "me."
                    )
                }
            }

            P(attrs = Modifier.align(Alignment.CenterHorizontally).toAttrs()) {
                Text("I am currently open full-time, contract or part-time opportunities in Mobile and Backend Development")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
                    .styleModifier {
                        ".input" style {
                            marginBottom(16.px)
                            if (breakpoint <= Breakpoint.MD) {
                                width(350.px)
                            } else {
                                width(450.px)
                            }
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextInput(
                    text = "",
                    onTextChanged = {

                    },
                    placeholder = "Email",
                    required = true,
                    size = InputSize.LG, modifier = Modifier.classNames("input")
                )

                TextInput(
                    text = "",
                    onTextChanged = {

                    },
                    placeholder = "FullName",
                    required = true,
                    size = InputSize.LG,
                    modifier = Modifier.classNames("input")
                )

                TextInput(
                    text = "",
                    onTextChanged = {

                    },
                    placeholder = "Message",
                    required = true,
                    size = InputSize.LG,
                    modifier = Modifier.classNames("input").height(200.px)
                )

                Button(
                    onClick = {}, modifier = Modifier
                        .classNames("input")
                        .margin(topBottom = 12.px)
                ) {
                    Text("Send Message")
                }

            }


        }

        Footer(
            attrs = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(200.px)
                .margin(0.px)
                .styleModifier { property("background-color", theme.backgroundColor2) }.toAttrs()
        ) {

        }

    }
}