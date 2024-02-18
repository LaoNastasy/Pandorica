package com.example.pandorica.ui.authorization

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pandorica.R


@Composable
fun CreateAccountScreen(
    viewModel: AuthorizationViewModel,
) {
    val state = viewModel.state.collectAsState().value

    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(state.successPopup) {
        if (state.successPopup) {
            Toast.makeText(
                context,
                "Success!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column {
        Text(
            text = if (state.authMethod == AuthorizationMethod.createAccount) {
                stringResource(R.string.create_account)
            } else {
                stringResource(R.string.sign_in)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
                .padding(top = 96.dp, bottom = 32.dp),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .fillMaxWidth(),
            value = state.login,
            onValueChange = { viewModel.onLoginChanged(it) },
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.login))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .fillMaxWidth(),
            value = state.password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.password))
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 64.dp, end = 64.dp, top = 32.dp),
            onClick = {
                localFocusManager.clearFocus()
                if (state.authMethod == AuthorizationMethod.createAccount) {
                    viewModel.createAccount()
                } else {
                    viewModel.signIn()
                }
            },

            ) {
            Text(
                text = if (state.authMethod == AuthorizationMethod.createAccount) {
                    stringResource(R.string.create)
                } else {
                    stringResource(R.string.sign_in)
                },
                fontSize = 16.sp
            )
        }
        ClickableText(
            text = AnnotatedString(
                if (state.authMethod == AuthorizationMethod.createAccount) {
                    stringResource(R.string.sign_in)
                } else {
                    stringResource(R.string.register)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 32.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.Right,
                textDecoration = TextDecoration.Underline
            ),
            onClick = {
                viewModel.changeAuthMethod()
            }
        )
    }
}