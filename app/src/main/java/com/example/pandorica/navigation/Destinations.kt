package com.example.pandorica.navigation

object AuthorizationDestination: NavigationDestination{
    override fun route() = "authorization"
}

object PasswordListDestination: NavigationDestination{
    override fun route() = "passwords_list"
}