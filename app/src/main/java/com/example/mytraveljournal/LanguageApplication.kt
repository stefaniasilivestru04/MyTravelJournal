package com.example.mytraveljournal

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.preference.PreferenceManager

class LanguageApplication : Application() {
    companion object {
        var selectedLanguage: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        selectedLanguage = PreferenceManager.getDefaultSharedPreferences(this)
            .getString("language", "en")
        updateAppLanguage(selectedLanguage!!)
    }

    fun updateAppLanguage(language: String) {
        selectedLanguage = language
    }

}