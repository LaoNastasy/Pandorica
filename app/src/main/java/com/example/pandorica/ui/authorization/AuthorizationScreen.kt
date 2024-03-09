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
import androidx.navigation.NavController
import com.example.pandorica.R
import com.example.pandorica.navigation.PasswordListDestination
import com.example.pandorica.ui.shared.Loader


@Composable
fun CreateAccountScreen(
    viewModel: AuthorizationViewModel,
    navController: NavController,
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(state.successPopup) {
        if (state.successPopup) {
            Toast.makeText(
                context,
                "Success!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(state.enterApplication) {
        if (state.enterApplication) {
            navController.navigate(PasswordListDestination.route())
            viewModel.onEnterApplicationHandled()
        }
    }

    when {
        state.loading -> Loader()
        else -> Content(
            authMethod = state.authMethod,
            login = state.login,
            password = state.password,
            onLoginChanged = viewModel::onLoginChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            onCreateAccountClick = viewModel::onCreateAccountClick,
            onSignInClick = viewModel::onSignInClick,
            onChangeAuthMethodClick = viewModel::onChangeAuthMethodClick
        )
    }

}

@Composable
private fun Content(
    authMethod: AuthorizationMethod,
    login: String,
    password: String,
    onLoginChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onCreateAccountClick: () -> Unit,
    onSignInClick: () -> Unit,
    onChangeAuthMethodClick: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column {
        Text(
            text = if (authMethod == AuthorizationMethod.createAccount) {
                stringResource(R.string.auth_create_account)
            } else {
                stringResource(R.string.auth_sign_in)
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
            value = login,
            onValueChange = onLoginChanged,
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.auth_login))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChanged,
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(text = stringResource(R.string.auth_password))
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 64.dp, end = 64.dp, top = 32.dp),
            onClick = {
                localFocusManager.clearFocus()
                if (authMethod == AuthorizationMethod.createAccount) {
                    onCreateAccountClick()
                } else {
                    onSignInClick()
                }
            },

            ) {
            Text(
                text = if (authMethod == AuthorizationMethod.createAccount) {
                    stringResource(R.string.auth_create_button)
                } else {
                    stringResource(R.string.auth_sign_in)
                },
                fontSize = 16.sp
            )
        }
        ClickableText(
            text = AnnotatedString(
                if (authMethod == AuthorizationMethod.createAccount) {
                    stringResource(R.string.auth_sign_in)
                } else {
                    stringResource(R.string.auth_register)
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
            onClick = { onChangeAuthMethodClick() }
        )
    }
}