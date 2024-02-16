package com.example.pandorica.ui.passwordList

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pandorica.R


@Composable
fun PasswordListScreen(
    viewModel: PasswordListViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = state.successPopup, block = {
        if (state.successPopup) {
            Toast.makeText(
                context,
                "Success!",
                Toast.LENGTH_LONG
            ).show()
        }
    })

    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.create_account),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp),
            fontSize = 36.sp
        )
        OutlinedTextField(
            value = viewModel.login,
            onValueChange = { viewModel.updateLogin(it) },
//            placeholder = {
//                Text(text = stringResource(R.string.login))
//            },
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.login))
            }
        )

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.updatePassword(it) },
//            placeholder = {
//                Text(text = stringResource(R.string.password))
//            },
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.password))
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.createAccount()
                localFocusManager.clearFocus()
            },

            ) {
            Text(
                text = stringResource(R.string.create),
                fontSize = 16.sp
            )
        }
    }


}