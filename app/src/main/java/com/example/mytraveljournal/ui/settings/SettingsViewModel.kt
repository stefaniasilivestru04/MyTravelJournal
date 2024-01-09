package com.example.mytraveljournal.ui.settings

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytraveljournal.R

class SettingsViewModel() : ViewModel() {
    private val _textTheme = MutableLiveData<String>().apply {
        value =""
    }

    private val _textLanguage = MutableLiveData<String>().apply {
        value = ""
    }
    val textLanguage: LiveData<String> = _textLanguage
    val textTheme: LiveData<String> = _textTheme

    fun setThemeText(text: String) {
        _textTheme.value = text
    }
    fun setLanguageText(text: String) {
        _textLanguage.value = text
    }


}