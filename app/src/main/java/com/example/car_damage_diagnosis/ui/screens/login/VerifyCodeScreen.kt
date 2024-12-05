package com.example.car_damage_diagnosis.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun VerifyCodeScreen(
    phoneNumber: String,
    onNavigateToHome: () -> Unit,
    onBackToPhone: () -> Unit,
    viewModel: VerifyCodeViewModel = hiltViewModel()
) {
    val verificationCode by viewModel.verificationCode.collectAsState()
    val isValidCode by viewModel.isValidCode.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val remainingTime by viewModel.remainingResendTime.collectAsState()

    LaunchedEffect(phoneNumber) {
        viewModel.setPhoneNumber(phoneNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Код подтверждения") },
                navigationIcon = {
                    IconButton(onClick = onBackToPhone) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft, 
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Введите код подтверждения",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Код отправлен на +7${phoneNumber}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = verificationCode,
                onValueChange = { viewModel.updateVerificationCode(it) },
                label = { Text("Код подтверждения") },
                placeholder = { Text("Введите 6-значный код") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { 
                    if (isValidCode) {
                        viewModel.verifyCode(onNavigateToHome)
                    }
                },
                enabled = isValidCode && !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Подтвердить")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (remainingTime > 0) {
                Text(
                    text = "Отправить повторно через ${remainingTime}с",
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                TextButton(
                    onClick = { viewModel.resendCode() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Отправить повторно")
                }
            }
        }
    }
}
