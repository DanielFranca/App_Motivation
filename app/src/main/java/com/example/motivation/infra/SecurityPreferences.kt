package com.example.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, str: String){
        this.sharedPreferences.edit().putString(key, str).apply()
    }

    fun getString(key: String): String? {
        return this.sharedPreferences.getString(key, "Insira seu nome.") ?: ""
    }

    fun getStoredString(key: String): String {
        return this.sharedPreferences.getString(key, "Insira seu nome. ") ?: ""
    }
}