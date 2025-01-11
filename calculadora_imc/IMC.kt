package com.a039679.calculadora_imc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun IMC(navController: NavHostController) {
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    val pattern = remember { Regex("^\\d+\$") }
    val result = remember { mutableStateOf(0F) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
    ) {
        Text(text = "Calcular o IMC", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 16.dp))

        Spacer(modifier = Modifier.height(64.dp))

        TextField(
            value = altura,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    altura = it
                }
            },
            label = { Text("Altura") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = peso,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    peso = it
                }
            },
            label = { Text("Peso") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            var calcA = altura.toFloat()
            val calcP = peso.toFloat()

            calcA /= 100F
            val calc = calcA * calcA
            result.value = calcP / calc
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (result.value != 0F) {
            Text("O seu IMC é: ${result.value}")

            if (result.value < 18.5F) {
                Text("Baixo Peso")
            } else if (result.value >= 18.5F && result.value < 25F) {
                Text("Peso Normal")
            } else if (result.value >= 25F && result.value < 30F) {
                Text("Excesso de Peso")
            } else if (result.value >= 30F) {
                Text("Obesidade")
            }
        } else {
            Text("")
        }

    }
}