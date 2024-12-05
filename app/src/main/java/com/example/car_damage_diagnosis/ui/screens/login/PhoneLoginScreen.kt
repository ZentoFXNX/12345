package com.example.car_damage_diagnosis.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PhoneLoginScreen(
    onNavigateToVerification: (String) -> Unit,
    onBackToWelcome: () -> Unit,
    viewModel: PhoneLoginViewModel = hiltViewModel()
) {
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val isValidPhoneNumber by viewModel.isValidPhoneNumber.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ввод номера телефона") },
                navigationIcon = {
                    IconButton(onClick = onBackToWelcome) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back to Welcome"
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
                text = "Введите номер телефона",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Мы пришлем вам код подтверждения СМС сообщением",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { 
                    viewModel.updatePhoneNumber(it.filter { char -> char.isDigit() }) 
                },
                label = { Text("Номер телефона") },
                prefix = { Text("+7 ") },
                placeholder = { Text("9991234567") },
                leadingIcon = { 
                    Icon(
                        imageVector = Icons.Default.Phone, 
                        contentDescription = "Phone Number"
                    ) 
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                maxLines = 1,
                isError = phoneNumber.isNotEmpty() && phoneNumber.length < 10,
                supportingText = {
                    if (phoneNumber.isNotEmpty() && phoneNumber.length < 10) {
                        Text("Введите полный номер телефона")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { 
                    viewModel.sendVerificationCode()
                    onNavigateToVerification(phoneNumber) 
                },
                enabled = isValidPhoneNumber && !isLoading,
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
                    Text("Отправить код подтверждения")
                }
            }
        }
    }
}
