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
            // Adicionar títulos das colunas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "IMC",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Data",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.35f)
                )
            }

            Divider() // Linha abaixo dos títulos
            
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)) {
                items(history) { entry ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "%.2f".format(entry.second), style = MaterialTheme.typography.bodyLarge)
                        Text(text = entry.first, style = MaterialTheme.typography.bodyLarge)
                    }
                    Divider()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { historyViewModel.clearHistory() }) {
            Text("Limpar Histórico")
        }
    }
}
