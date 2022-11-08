package com.easy.meet.screens.addAttendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.BorderButton
import com.easy.meet.components.InputField
import com.easy.meet.components.ShowTitle
import com.easy.meet.components.ShowTopBar
import com.easy.meet.ui.theme.ColorPrimary

@ExperimentalMaterialApi
@Composable
fun AddAttendanceScreen(navController: NavController) {
    Scaffold(topBar = {
        ShowTopBar(
            modifier = Modifier.height(70.dp),
            text = stringResource(R.string.addAttendance),
            icon = Icons.Default.ArrowBack,
            elevation = 10.dp,
            color = colorResource(id = R.color.white)
        ) {
            navController.popBackStack()
        }
    }) {
        AddAttendanceLayout()
    }
}

@ExperimentalMaterialApi
@Composable
fun AddAttendanceLayout() {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        val name = remember { mutableStateOf("") }
        val comments = remember { mutableStateOf("") }

        ShowTitle(
            title = stringResource(id = R.string.name),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
        InputField(
            value = name,
            placeholder = stringResource(id = R.string.name),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.comments),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        InputField(
            value = comments,
            placeholder = stringResource(id = R.string.comments),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.schedule),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        repeat(4) {
            DateConfirmingView()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }

        BorderButton(
            text = stringResource(R.string.save), modifier = Modifier
                .padding(5.dp)
                .padding(5.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        ) {

        }

    }
}

@ExperimentalMaterialApi
@Composable
fun DateConfirmingView() {
    val images = listOf(Icons.Default.Check, Icons.Default.Help, Icons.Default.Close)
    var selectedOption by remember {
        mutableStateOf(Icons.Default.Close)
    }
    val onSelectionChange = { image: ImageVector ->
        selectedOption = image
    }
    Row(
        modifier = Modifier
            .padding(1.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        Text(text = "01 Nov 22 ( Wed ) ")

        images.forEach { image ->
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(shape = CircleShape)
                            .background(
                                if (image == selectedOption) {
                                    ColorPrimary
                                } else {
                                    Color.LightGray
                                }
                            ),
                        onClick = { onSelectionChange(image) }) {
                        Icon(
                            imageVector = image,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }

    }
}
