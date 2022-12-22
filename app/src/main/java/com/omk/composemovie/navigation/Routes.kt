package com.omk.composemovie.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Details : Routes("details")
}