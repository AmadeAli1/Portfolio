package com.amade.dev

import androidx.compose.runtime.Composable
import com.amade.dev.components.projectCardStyle
import com.amade.dev.components.whatIDoCardStyle
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    // Configure silk here
    ctx.theme.registerComponentStyle(whatIDoCardStyle)
    ctx.theme.registerComponentStyle(projectCardStyle)
    ctx.stylesheet.registerStyle("html") {
        this.base {
            Modifier.styleModifier {
                property("scroll-behavior", "smooth")
            }
        }
    }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().fillMaxSize(), colorModeOverride = ColorMode.DARK) {
            content()
        }
    }
}
