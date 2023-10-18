package com.group8.change.evaluations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.group8.change.R
import com.group8.change.components.TextFieldWithLabel
import com.group8.change.ui.design.TopAppBarPlus

@Composable
fun MonthlyEvaluationAddiction(navController: NavController) {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    
    TopAppBarPlus(content = {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 56.dp)
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            TextFieldWithLabel(
                labelText = stringResource(id = R.string.month_title1),
                textValue = text1,
                onTextChange = { newValue -> text1 = newValue }
            )

            TextFieldWithLabel(
                labelText = stringResource(id = R.string.month_title2),
                textValue = text2,
                onTextChange = { newValue -> text2 = newValue }
            )

            TextFieldWithLabel(
                labelText = stringResource(id = R.string.month_title3),
                textValue = text3,
                onTextChange = { newValue -> text3 = newValue }
            )
        }
    }, title = stringResource(id = R.string.card_title_monthly_evaluation),
        secondButton = { /*TODO*/ },
        navController = navController)
}