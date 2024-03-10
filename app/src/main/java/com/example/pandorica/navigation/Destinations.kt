package com.example.pandorica.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object AuthorizationDestination : NavigationDestination {
    override fun route() = "authorization"
}

object PasswordListDestination : NavigationDestination {
    override fun route() = "passwords_list"
}

object CreateAccountDestination : NavigationDestination {
    override fun route() = "create_account"
}

object DeleteAccountDestination : NavigationDestination {
    private const val ROUTE = "delete_account"
    private const val NAME_ARG = "name"

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(NAME_ARG) { type = NavType.StringType }
    )

    override fun route() = "$ROUTE/{$NAME_ARG}"

    fun createRoute(name: String) = "$ROUTE/${Uri.encode(name)}"
    fun extractName(savedStateHandle: SavedStateHandle): String = savedStateHandle[NAME_ARG]!!
}