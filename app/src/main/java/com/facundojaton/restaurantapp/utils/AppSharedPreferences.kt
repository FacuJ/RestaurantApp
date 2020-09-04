package com.autosolve.yasale.ui.utils

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreferences(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        const val APP_SHARED_PREFERENCES = "APP_SHARED_PREFERENCES"
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveBoolean(key: String, value: Boolean?) {
        val editor = sharedPreferences.edit()
        value?.let { editor.putBoolean(key, it) }
        editor.apply()
    }

    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, AppConstants.EMPTY_STRING)
    }

    fun getBoolean(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key, -1)
    }
}