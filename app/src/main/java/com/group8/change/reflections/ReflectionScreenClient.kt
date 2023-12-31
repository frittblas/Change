package com.group8.change.reflections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.group8.change.R
import com.group8.change.api.DBApi
import com.group8.change.api.models.CurrentAppData
import com.group8.change.api.viewmodel.MainViewModel
import com.group8.change.components.HistoricalDataButton
import com.group8.change.ui.design.TopAppBarPlus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReflectionScreenClient(navController: NavController) {
    AppTheme {
        var textState by remember { mutableStateOf(TextFieldValue("")) }
        var sliderPosition by remember{ mutableStateOf(5f) }

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
                TopAppBarPlus(
                    content = {

                    Column (modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxSize()
                        .background(Color.White)){
                        Spacer(modifier = Modifier.height(15.dp))
                    HistoricalDataButton(navController = navController, route = "reflections-th")
                        Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = textState,
                        onValueChange = { newText ->
                            textState = newText
                        },
                        label ={ Text(text = stringResource(id = R.string.reflections_textbox))} ,
                        textStyle = TextStyle(fontSize = 20.sp),
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Column (modifier = Modifier.padding(16.dp)){
                        ExperienceTexts()
                        Slider(
                            value = sliderPosition,
                            onValueChange = {sliderPosition = it},
                            valueRange= 0f..10f,
                            steps = 9
                        )
                        SelectedPositionText(sliderPosition)
                    }

                } }, title = stringResource(id = R.string.card_title_reflections),
                    navController = navController,
                    secondButton = { SubmitReflection(sliderPosition, textState.text,navController) }
                    )


        }
    }

}

@Composable
fun ExperienceTexts() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.reflections_bad))
        Text(text = stringResource(id = R.string.reflections_good))
    }
}

@Composable

fun SelectedPositionText(sliderPosition: Float) {
    val label = if (sliderPosition >= 0 && sliderPosition <= 1) {
        stringResource(id = R.string.reflections_very_bad)
    } else if (sliderPosition >= 2 && sliderPosition <= 4) {
        stringResource(id = R.string.reflections_bad)
    } else if (sliderPosition >= 5.9 && sliderPosition <= 8) {
        stringResource(id = R.string.reflections_good)
    } else if (sliderPosition >= 9 && sliderPosition <= 10) {
        stringResource(id = R.string.reflections_very_good)
    } else {
        ""
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Text(
                text = label,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }

    }
}

@Composable
fun SubmitReflection(startPosition: Float, textState: String,navController: NavController) {

    Button(
        onClick = {
            val currentDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
            val newReflection = com.group8.change.api.models.Reflection(textState, currentDate, startPosition.toInt())

            CurrentAppData.data.reflections.add(newReflection)
            DBApi.addChangesToDB(MainViewModel())

            navController.navigate("main-menu")
        }
    ) {
        Text(stringResource(id = R.string.submit_button_text),
            color = Color.White)
    }
}
