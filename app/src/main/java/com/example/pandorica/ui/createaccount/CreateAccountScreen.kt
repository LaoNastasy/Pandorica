package com.example.pandorica.ui.createaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pandorica.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(viewModel: CreateAccountViewModel) {
    val state = viewModel.state.collectAsState().value

    Column(Modifier.background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.create_account_title)) })
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.create_account_login),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(value = state.login, onValueChange = viewModel::onLoginChanged)
        Text(
            text = stringResource(id = R.string.create_account_password),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Button(onClick = viewModel::onCreateClick) {
            Text(text = stringResource(id = R.string.create_account_action))
        }
    }
}
