package com.example.pandorica.ui.deleteaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pandorica.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountDialog(viewModel: DeleteAccountViewModel, navController: NavController) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(state.goBack) {
        if (state.goBack) {
            navController.popBackStack()
            viewModel.onGoBackHandled()
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.delete_account_title)) })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.delete_account_text, state.name))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = viewModel::onConfirmClick) {
            Text(text = stringResource(id = R.string.delete_account_action))
        }
    }

}
