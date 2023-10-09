package com.group8.change

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun eveningEvaluation() {
    Column (
        modifier = Modifier
            .background(Color.White)
    ){
        topBarWithLogo()
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.card_title_evening_evaluation),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 64.dp),
                text = stringResource(id = R.string.evening_title1),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Text(
                text = stringResource(id = R.string.evening_title2),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Text(
                text = stringResource(id = R.string.evening_title3),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Text(
                text = stringResource(id = R.string.evening_title4),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Text(
                text = stringResource(id = R.string.evening_title5),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Text(
                text = stringResource(id = R.string.evening_title6),
                style = TextStyle(fontSize = 16.sp)
            )

            textField()

            Button(
                onClick = {

                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Submit")
            }

        }


        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun eveningEvaluationPreview() {
    eveningEvaluation()
}