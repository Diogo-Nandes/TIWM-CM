package com.example.imcapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val historyDataStore = HistoryDataStore(application)

    private val _history = MutableStateFlow<List<Pair<String, Double>>>(emptyList())
    val history: StateFlow<List<Pair<String, Double>>> = _history

    init {
        viewModelScope.launch {
            historyDataStore.historyFlow.collect { entries ->
                _history.value = entries
            }
        }
    }

    fun addEntry(date: String, bmi: Double) {
        viewModelScope.launch {
            historyDataStore.saveEntry(date, bmi)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            historyDataStore.clearHistory()
        }
    }
}
