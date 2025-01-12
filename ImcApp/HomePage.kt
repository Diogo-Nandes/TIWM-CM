package com.example.imcapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, historyViewModel: HistoryViewModel) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Calculadora de IMC", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 16.dp))

        Spacer(modifier = Modifier.height(64.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val weightValue = weight.toDoubleOrNull()
            val heightValue = height.toDoubleOrNull()
            if (weightValue != null && heightValue != null && heightValue > 0) {
                result = weightValue / (heightValue * heightValue)
                historyViewModel.addEntry(
                    date = java.time.LocalDate.now().toString(),
                    bmi = result!!
                )
            }
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text(text = "Seu IMC: %.2f".format(it))
        }
    }
}