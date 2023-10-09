package com.amade.dev.components

import androidx.compose.runtime.*
import com.amade.dev.styles.Color
import com.amade.dev.styles.Theme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaBox
import com.varabyte.kobweb.silk.components.icons.fa.FaIcon
import com.varabyte.kobweb.silk.components.icons.fa.IconCategory
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun NavBar(theme: Color) {
    val breakpoint = rememberBreakpoint()
    var showVerticalNavBar by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
            .styleModifier {
                if (breakpoint <= Breakpoint.SM) {
                    paddingRight(1.cssRem)
                    paddingLeft(1.cssRem)
                    paddingTop(1.cssRem)
                } else {
                    paddingRight(5.cssRem)
                    paddingLeft(5.cssRem)
                }
            }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H1(
                attrs = Modifier.styleModifier {
                    fontFamily("Skranji")
                    color(theme.secondaryColor)
                }.toAttrs()
            ) {
                Text("AL")
            }

            if (breakpoint > Breakpoint.SM) {
                HorizontalNavBar()
                NavMenus(size = IconSize.XXL)
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    NavMenus(size = IconSize.XL)
                    Button(
                        onClick = {
                            showVerticalNavBar = !showVerticalNavBar
                        },
                        modifier = Modifier.size(45.px).borderRadius(5.px)
                    ) {
                        FaBox()
                    }
                }
            }

        }

        if (showVerticalNavBar) {
            VerticalNavBar { showVerticalNavBar = false }
        }
    }
}

@Composable
private fun HorizontalNavBar() {
    val routes = NavigationBar.routes
    var selectedRoute by remember { mutableStateOf(routes.first()) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        routes.forEach { navLink ->
            NavLinkItem(
                navLink = navLink,
                selectedRoute = selectedRoute,
                onNavigate = { selectedRoute = navLink }
            )
        }
    }
}

@Composable
private fun VerticalNavBar(onNavigate: () -> Unit) {
    val routes = NavigationBar.routes
    var selectedRoute by remember { mutableStateOf(routes.first()) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        routes.forEach { navLink ->
            NavLinkItem(
                modifier = Modifier.styleModifier {
                    boxShadow("0 0.5px 0.5px")
                    marginBottom(8.px)
                },
                navLink = navLink,
                selectedRoute = selectedRoute,
                onNavigate = {
                    selectedRoute = navLink
                    onNavigate()
                }
            )
        }
    }
}


@Composable
private fun NavLinkItem(
    theme: Color = Theme,
    modifier: Modifier = Modifier,
    navLink: NavLink,
    selectedRoute: NavLink? = null,
    onNavigate: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.margin(right = 16.px)
    ) {
        Link(
            path = navLink.route,
            text = navLink.name,
            modifier = Modifier
                .styleModifier {
                    this.color(if (selectedRoute == navLink) theme.secondaryColor else theme.primaryColor)
                    fontFamily("Skranji")
                    fontWeight(400)
                }
                .textDecorationLine(TextDecorationLine.None)
                .onClick {
                    if (selectedRoute?.name != navLink.name) {
                        onNavigate()
                    }
                    document.getElementById(navLink.name)?.scrollIntoView()
                }
        )
        if (selectedRoute == navLink) {
            Box(
                modifier = Modifier
                    .width(30.px)
                    .height(3.5.px)
                    .margin(top = 2.px)
                    .borderRadius(5.px)
                    .styleModifier { property("background-color", theme.secondaryColor) }) { }
        }
    }
}

@Composable
private fun NavMenus(modifier: Modifier = Modifier, size: IconSize? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationBar.menus.forEach { menu ->
            A(href = menu.route, attrs = Modifier.color(Colors.White).toAttrs()) {
                FaIcon(
                    name = menu.icon,
                    size = size,
                    style = IconCategory.BRAND,
                    modifier = modifier
                        .margin(right = 8.px)
                        .cursor(Cursor.Pointer)

                )
            }
        }

    }
}

sealed class NavMenu(val icon: String, val route: String) {
    data object Github : NavMenu(icon = "github", route = "https://github.com/AmadeAli1")
    data object Linkedin : NavMenu(icon = "linkedin", route = "https://www.linkedin.com/in/amade-ali-22404924a/")
}

data class NavLink(val name: String, val route: String = "")
object NavigationBar {
    val routes by lazy {
        listOf(
            NavLink("Home"),
            NavLink("Projects"),
            NavLink("About"),
            //NavLink("Contact")
        )
    }

    val menus by lazy {
        listOf(
            NavMenu.Github, NavMenu.Linkedin
        )
    }
}