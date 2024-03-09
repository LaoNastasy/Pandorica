package com.example.pandorica.ui.passwordList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pandorica.R
import com.example.pandorica.network.entity.vault.PasswordEntry
import com.example.pandorica.ui.shared.ErrorContent
import com.example.pandorica.ui.shared.Loader
import com.example.pandorica.ui.theme.PandoricaTheme
import com.example.pandorica.ui.theme.PurpleGrey80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordListScreen(
    viewModel: PasswordListViewModel
) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.password_list_title))
                }
            )
        }
    ) { paddingValues ->
        when {
            state.error -> ErrorContent(viewModel::onReload)
            state.loading -> Loader()
            else -> {
                LazyColumn(Modifier.padding(paddingValues)) {
                    items(state.passwordEntries) { Item(it) }
                }
            }
        }
    }
}

@Composable
private fun Item(passwordEntry: PasswordEntry) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80)

    ) {
        Text(
            text = stringResource(
                id = R.string.password_list_login,
                passwordEntry.encodedLogin ?: ""
            ), modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(
                id = R.string.password_list_password,
                passwordEntry.encodedPassword
            ), modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview
@Composable
private fun Preview() {
    PandoricaTheme {
        Item(passwordEntry = PasswordEntry("login", "password"))
    }
}