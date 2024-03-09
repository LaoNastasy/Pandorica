package com.example.pandorica.ui.passwordList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pandorica.network.entity.vault.PasswordEntry
import com.example.pandorica.ui.theme.PandoricaTheme


@Composable
fun PasswordListScreen(
    viewModel: PasswordListViewModel
) {
    val state = viewModel.state.collectAsState().value

    LazyColumn {
        items(state.passwordEntries) { Item(it) }
    }
}

@Composable
private fun Item(passwordEntry: PasswordEntry) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(text = passwordEntry.encodedLogin ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = passwordEntry.encodedPassword)
    }
}

@Preview
@Composable
private fun Preview() {
    PandoricaTheme {
        Item(passwordEntry = PasswordEntry("login", "password"))
    }
}