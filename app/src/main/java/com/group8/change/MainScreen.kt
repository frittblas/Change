package com.group8.change

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.compose.change_background
import com.group8.change.api.DBApi
import com.group8.change.api.sealed.AppDataState
import com.group8.change.api.viewmodel.MainViewModel
import com.group8.change.components.MainMenu
import com.group8.change.evaluations.SubmitMonthEvaluationAddiction
import com.group8.change.ui.design.TopAppBar
import com.group8.change.ui.design.TopAppBarPlus

// Composable building the home screen
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {
    TopAppBarPlus(
        content = {
            when (val result = viewModel.appDataState.value) {

                is AppDataState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator();
                    }
                }

                is AppDataState.Success -> {

                    DBApi.setCurrentAppData(viewModel, "")   // set the singleton to current appData

                    // Building the main menu
                    MainMenu(navController = navController,
                        viewModel = viewModel
                    )

                }

                is AppDataState.Failure -> {}
                else -> {}

            }
        },
        title = stringResource(id = R.string.app_name),
        secondButton = {
            LogoutUser(navController)
        },
        navController= navController
    )
}

@Composable
fun LogoutUser(navController: NavController) {
    Button(onClick = {
        DBApi.logout()
        navController.navigate("login")
    }) {
        Text(stringResource(id = R.string.logout_button_text),
            color = Color.White)

    }
}
