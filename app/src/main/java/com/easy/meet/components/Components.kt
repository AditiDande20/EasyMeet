package com.easy.meet.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.easy.meet.R
import com.easy.meet.ui.theme.ColorAccent
import com.easy.meet.ui.theme.ColorPrimary
import com.easy.meet.ui.theme.ColorPrimaryDark
import com.easy.meet.ui.theme.QuickSand
import com.easy.meet.utils.Utils
import com.squaredem.composecalendar.ComposeCalendar
import java.util.*

@Composable
fun ShowAppImage(
    image: Int,
    modifier: Modifier = Modifier,
    tint: Color
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = stringResource(id = R.string.image),
        modifier = modifier,
        colorFilter = ColorFilter.tint(tint)
    )
}

@Composable
fun ShowTitle(
    title: String,
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = title,
        modifier = modifier,
        color = color,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, fontFamily = QuickSand)
    )
}

@Composable
fun ShowInputField(
    value: MutableState<String>,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    passwordVisibility: MutableState<Boolean>? = null,
    onActions: KeyboardActions = KeyboardActions.Default
) {
    Card(
        shape = RoundedCornerShape(corner = CornerSize(2.dp)),
        elevation = 3.dp,
        modifier = modifier
    ) {
        TextField(
            value = value.value,
            onValueChange = { value.value = it },
            enabled = enabled,
            placeholder = {
                Text(text = placeholder, fontFamily = QuickSand)
            },
            textStyle = TextStyle(fontSize = 14.sp, fontFamily = QuickSand),
            maxLines = 1,
            singleLine = true,
            shape = RectangleShape,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = onActions,
            trailingIcon = { if (passwordVisibility != null) PasswordVisibility(passwordVisibility = passwordVisibility) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun InputField(
    value: MutableState<String>,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    iconVisibility: Boolean = false,
    onActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value.value,
        modifier = modifier.height(50.dp),
        onValueChange = { value.value = it },
        enabled = enabled,
        placeholder = {
            Text(text = placeholder)
        },
        textStyle = TextStyle(fontSize = 14.sp),
        maxLines = 1,
        singleLine = true,
        shape = RectangleShape,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = onActions,
        trailingIcon = { if (iconVisibility) Icons.Default.DateRange },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = White,
            focusedBorderColor = ColorPrimary,
            unfocusedBorderColor = Color.Gray,
        )
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}

@Composable
fun ShowButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(corner = CornerSize(3.dp)),
    ) {
        Text(text = text, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
    }

}

@Composable
fun ShowOptions(modifier: Modifier = Modifier, text: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text, modifier = Modifier
                .padding(2.dp)
                .height(20.dp), style = TextStyle(fontSize = 14.sp)
        )

        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShowOptionImage(
                modifier = Modifier
                    .padding(5.dp)
                    .height(50.dp)
                    .width(90.dp),
                icon = R.drawable.ic_google
            )

            Spacer(modifier = Modifier.width(10.dp))

            ShowOptionImage(
                modifier = Modifier
                    .padding(5.dp)
                    .height(50.dp)
                    .width(90.dp),
                icon = R.drawable.ic_facebook
            ) {
            }

            Spacer(modifier = Modifier.width(10.dp))

            ShowOptionImage(
                modifier = Modifier
                    .padding(5.dp)
                    .height(50.dp)
                    .width(90.dp),
                icon = R.drawable.ic_twitter
            )

        }

    }
}

@Composable
fun ShowOptionImage(modifier: Modifier, icon: Int, onClick: () -> Unit = {}) {
    Card(modifier = modifier.clickable {
        onClick.invoke()
    }, elevation = 2.dp) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.image),
            modifier = Modifier
                .padding(15.dp)
                .height(10.dp)
                .width(10.dp)
        )
    }
}

@Composable
fun ShowTopBar(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector?,
    elevation: Dp,
    color: Color,
    onBackPress: () -> Unit = {}
) {
    TopAppBar(modifier = modifier, elevation = elevation, backgroundColor = color, title = {
        Row(modifier = Modifier.padding(2.dp), verticalAlignment = CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.image),
                    tint = ColorPrimaryDark,
                    modifier = Modifier.clickable { onBackPress.invoke() }
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = text, color = ColorPrimary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = QuickSand
                )
            )
        }
    })
}

@Composable
fun CreateChips(date: String, onRemove: (String) -> Unit) {

    val time = date.split(" ")[2]
    val day = date.split(" ")[0].substringBefore("-")
    val month = date.split(" ")[0].substringAfter("-").slice(0..2)

    Box(
        modifier = Modifier
            .padding(top = 10.dp, start = 2.dp, end = 2.dp)
            .padding(3.dp)
            .wrapContentWidth()
            .background(
                shape = RoundedCornerShape(10.dp),
                color = ColorPrimary
            )
            .clickable {
                Log.e("Aditi==>", "CreateChips remove :: $date")
                onRemove.invoke(date)
            }
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = QuickSand,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1
            )

            Text(
                text = month,
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )

            Text(
                text = time,
                modifier = Modifier
                    .padding(2.dp)
                    .padding(2.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = QuickSand,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            /*Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp)
                    .clickable { },
                imageVector = Icons.Default.Close,
                tint = Color.White,
                contentDescription = null
            )*/
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowChipGroup(dateChipList: MutableList<String>, onRemove: (String) -> Unit) {
    LazyRow {
        items(dateChipList.size) { chipDate ->
            if (dateChipList[chipDate].isNotEmpty() && dateChipList[chipDate].isNotBlank()) {
                CreateChips(dateChipList[chipDate]) {
                    onRemove.invoke(it)
                }
            }
        }
    }
}

@Composable
fun BorderButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick.invoke()
        }, elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier,
        border = BorderStroke(1.dp, ColorPrimary),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorPrimary)
    ) {
        Text(
            text = text,
            color = ColorPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ShowTitle(text: String, description: String) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .padding(2.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(1.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = ColorPrimary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
    }
}

@Composable
fun ListLayout(
    eventTitle: String,
    dateCreated: String,
    eventPlace: String,
    eventDate: String?,
    eventStatus: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick.invoke() },
        elevation = 10.dp,
        backgroundColor = White
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .padding(2.dp)
            ) {
                Text(
                    text = eventTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(2.dp)
                        .padding(2.dp)
                        .fillMaxWidth(0.7f)
                )

                Text(
                    text = dateCreated,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    maxLines = 1,
                    textAlign = TextAlign.End,
                    color = ColorPrimary,
                    modifier = Modifier
                        .padding(2.dp)
                        .padding(2.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .padding(2.dp)
            ) {

                ShowImageWithText(Icons.Default.LocationOn, eventPlace)

                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    modifier = Modifier
                        .padding(1.dp)
                        .padding(1.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (eventStatus == "Confirmed") ColorPrimary else ColorAccent
                    )
                ) {
                    Text(
                        text = eventStatus,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        color = if (eventStatus == "Confirmed") ColorPrimary else ColorAccent,
                        modifier = Modifier
                            .padding(2.dp)
                            .padding(2.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .padding(2.dp)
            ) {
                if (eventDate != null) {
                    ShowImageWithText(Icons.Default.DateRange, eventDate)
                }
            }

        }
    }
}

@Composable
fun ShowImageWithText(image: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .padding(2.dp)
            .padding(2.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp),
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = Color.LightGray

        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowButtonIcons(image: ImageVector) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        IconButton(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = CircleShape)
                .background(
                    ColorPrimary
                ),
            onClick = { }) {
            Icon(
                imageVector = image,
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Composable
fun DatePickerCompose(onClose: () -> Unit, onDone: (String) -> Unit) {
    ComposeCalendar(
        onDone = {
            val date = "${it.dayOfMonth}-${
                Utils.capitalizeWords(it.month.toString()).slice(0..2)
            }-${it.year.toString().slice(2..3)} ${
                Utils.capitalizeWords(it.dayOfWeek.toString()).slice(0..2)
            }"
            onDone(date)
            onClose.invoke()
        },
        onDismiss = {
            onClose.invoke()
        }
    )
}

@Composable
fun TimePickerView(onClose: () -> Unit, onDone: (String) -> Unit) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val min = mCalendar[Calendar.MINUTE]

    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext, R.style.TimePicker,
        { _: TimePicker?, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
            onDone(mTime.value)
            onClose.invoke()
        }, hour, min, false
    )

    mTimePickerDialog.show()
}

@Composable
fun ShowProgressDialog(modifier: Modifier, title: String, description: String) {
    val isPlaying by remember { mutableStateOf(true) }
    val speed by remember { mutableStateOf(1f) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.event))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )

    Box(
        contentAlignment = Center,
        modifier = Modifier
            .background(Transparent, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = title,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = White,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = description,
                fontFamily = QuickSand,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = White,
                maxLines = 3
            )
        }
    }
}




