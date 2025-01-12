package com.example.imcapp

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("history_prefs")

class HistoryDataStore(private val context: Context) {
    private val gson = Gson()
    private val historyKey = stringPreferencesKey("history_list")

    // Recuperar o histórico como uma lista
    val historyFlow: Flow<List<Pair<String, Double>>> = context.dataStore.data.map { preferences ->
        val json = preferences[historyKey] ?: "[]"
        val type = object : TypeToken<List<Pair<String, Double>>>() {}.type
        gson.fromJson(json, type)
    }

    // Guardar uma nova entrada no histórico
    suspend fun saveEntry(date: String, bmi: Double) {
        context.dataStore.edit { preferences ->
            val currentHistory = preferences[historyKey]?.let {
                val type = object : TypeToken<List<Pair<String, Double>>>() {}.type
                gson.fromJson<List<Pair<String, Double>>>(it, type)
            } ?: emptyList()

            val updatedHistory = currentHistory + Pair(date, bmi)
            preferences[historyKey] = gson.toJson(updatedHistory)
        }
    }

    // Limpar o histórico
    suspend fun clearHistory() {
        context.dataStore.edit { preferences ->
            preferences.remove(historyKey)
        }
    }
}
