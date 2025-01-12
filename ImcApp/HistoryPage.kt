package com.example.imcapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryPage(historyViewModel: HistoryViewModel) {
    val history = historyViewModel.history.collectAsState().value

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)) {
        Text(text = "Histórico", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        if (history.isEmpty()) {
            Text(text = "Nenhum histórico disponível.")
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(history) { entry ->
                    Text(text = "${entry.first}: ${entry.second}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { historyViewModel.clearHistory() }) {
            Text("Limpar Histórico")
        }
    }
}
