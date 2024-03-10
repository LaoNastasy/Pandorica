package com.example.pandorica.ui.passwordList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.pandorica.R
import com.example.pandorica.navigation.CreateAccountDestination
import com.example.pandorica.network.entity.vault.PasswordEntry
import com.example.pandorica.ui.shared.ErrorContent
import com.example.pandorica.ui.shared.Loader
import com.example.pandorica.ui.shared.OnLifecycleEvent
import com.example.pandorica.ui.theme.PandoricaTheme
import com.example.pandorica.ui.theme.PurpleGrey80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordListScreen(
    viewModel: PasswordListViewModel,
    navController: NavController,
) {
    val state = viewModel.state.collectAsState().value

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) viewModel.onReload()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.password_list_title))
                },
                actions = {
                    IconButton(onClick = { navController.navigate(CreateAccountDestination.route()) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (state.error) {
            ErrorContent(viewModel::onReload)
        } else {
            LazyColumn(Modifier.padding(paddingValues)) {
                items(state.passwordEntries.sortedBy { it.timestamp }) {
                    Item(
                        passwordEntry = it,
                        onDeleteClick = viewModel::onDeleteClick
                    )
                }
            }
        }
        if (state.loading) Loader()
    }
}

@Composable
private fun Item(passwordEntry: PasswordEntry, onDeleteClick: (PasswordEntry) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Column {
                if (passwordEntry.name != null) {
                    Text(
                        text = stringResource(
                            id = R.string.password_list_name,
                            passwordEntry.name
                        ), modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Text(
                    text = stringResource(
                        id = R.string.password_list_login,
                        passwordEntry.encodedLogin ?: ""
                    ), modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = stringResource(
                        id = R.string.password_list_password,
                        passwordEntry.encodedPassword
                    ), modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            IconButton(onClick = { onDeleteClick.invoke(passwordEntry) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    PandoricaTheme {
        Item(
            passwordEntry = PasswordEntry("name", "login", "password"),
            onDeleteClick = {}
        )
    }
}