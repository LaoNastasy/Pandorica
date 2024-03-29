package com.example.pandorica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.pandorica.navigation.AuthorizationDestination
import com.example.pandorica.navigation.CreateAccountDestination
import com.example.pandorica.navigation.DeleteAccountDestination
import com.example.pandorica.navigation.NavigationDestination
import com.example.pandorica.navigation.PasswordListDestination
import com.example.pandorica.ui.authorization.AuthorizationScreen
import com.example.pandorica.ui.createaccount.CreateAccountScreen
import com.example.pandorica.ui.deleteaccount.DeleteAccountDialog
import com.example.pandorica.ui.passwordList.PasswordListScreen
import com.example.pandorica.ui.theme.PandoricaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PandoricaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavHost()
                }
            }
        }
    }
}

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AuthorizationDestination.route()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        destinations(navController).forEach { entry ->
            val destination = entry.key
            composable(
                route = destination.route(),
                arguments = destination.arguments,
            ) {
                entry.value()
            }
        }

        dialogDestinations(navController).forEach { entry ->
            val destination = entry.key
            dialog(
                route = destination.route(),
                arguments = destination.arguments,
            ) {
                entry.value()
            }
        }
    }
}

private fun destinations(
    navController: NavController,
) = mapOf<NavigationDestination, @Composable () -> Unit>(
    AuthorizationDestination to {
        AuthorizationScreen(
            viewModel = hiltViewModel(),
            navController = navController,
        )
    },
    PasswordListDestination to {
        PasswordListScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }
)

private fun dialogDestinations(
    navController: NavController,
) = mapOf<NavigationDestination, @Composable () -> Unit>(
    CreateAccountDestination to {
        CreateAccountScreen(viewModel = hiltViewModel(), navController = navController)
    },
    DeleteAccountDestination to {
        DeleteAccountDialog(viewModel = hiltViewModel(), navController = navController)
    }
)