package com.easy.meet.screens.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.*
import com.easy.meet.models.User
import com.easy.meet.navigation.EasyMeetScreens
import com.easy.meet.screens.authentication.viewmodel.AuthenticationViewModel
import com.easy.meet.ui.theme.ColorPrimaryDark
import com.easy.meet.utils.Utils
import com.easy.meet.utils.Utils.showToast

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {
    val showLoginPage = remember { mutableStateOf(true) }
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.smoky_white)) {
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AuthenticationForm(showLoginPage) { email, password,name ->
                    val createdDate = Utils.getCurrentDateTime()
                    if(showLoginPage.value){
                        authenticationViewModel.signInWithEmailPassword(email,password){
                            showToast(context,"Sign In Successful")
                            navController.navigate(EasyMeetScreens.HomeScreen.name)
                        }
                    }else{
                        authenticationViewModel.createUserWithEmailPassword(email,password){
                            val id = Utils.getCurrentUserID()
                            val user = User(id,name,email,password,createdDate,createdDate)
                            authenticationViewModel.insertUser(user)
                            navController.navigate(EasyMeetScreens.HomeScreen.name)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun AuthenticationForm(
    showLoginPage: MutableState<Boolean>,
    onDone: (String, String,String) -> Unit = { emailID, pass, name -> }
) {

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val focus = FocusRequester.Default

    ShowTopBar(
        text = "",
        icon = if (!showLoginPage.value) {
            Icons.Default.ArrowBack
        } else null,
        elevation = 0.dp,
        color = colorResource(id = R.color.smoky_white)
    ) {
        if (!showLoginPage.value) showLoginPage.value = !showLoginPage.value
    }

    ShowAppImage(
        image = R.drawable.app_image,
        modifier = Modifier.padding(bottom = 10.dp),
        tint = ColorPrimaryDark
    )

    ShowTitle(
        title = if (showLoginPage.value) {
            stringResource(id = R.string.login)
        } else {
            stringResource(id = R.string.register)
        },
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        color = Color.Gray
    )

    if (!showLoginPage.value) {
        ShowInputField(
            value = name,
            placeholder = stringResource(id = R.string.name),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )
    }

    ShowInputField(
        value = email,
        placeholder = stringResource(id = R.string.email),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
        onActions = KeyboardActions {
            focus.requestFocus()
        }
    )

    ShowInputField(
        value = password,
        placeholder = stringResource(id = R.string.password),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password,
        passwordVisibility = passwordVisibility
    )

    if (!showLoginPage.value) {
        ShowInputField(
            value = confirmPassword,
            placeholder = stringResource(id = R.string.confirmPassword),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            passwordVisibility = passwordVisibility
        )
    }

    ShowButton(
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(), text = if (showLoginPage.value) {
            stringResource(id = R.string.signIn)
        } else {
            stringResource(id = R.string.signUp)
        }, enabled = true
    ) {

        if (valid) {
            onDone(email.value.trim(), password.value.trim(),name.value.trim())
        }
    }

    ShowOptions(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(), text = stringResource(
            id = if (showLoginPage.value) {
                R.string.LoginOptionsText
            } else {
                R.string.RegistrationOptionsText
            }
        )
    )

    if (showLoginPage.value) {
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append(stringResource(id = R.string.navigateToRegister))
            }
            withStyle(
                style = SpanStyle(
                    color = ColorPrimaryDark,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(id = R.string.signUp))
            }
        }

        Text(
            text = text,
            modifier = Modifier
                .clickable {
                    showLoginPage.value = !showLoginPage.value
                }
                .padding(5.dp)
        )
    }
}
