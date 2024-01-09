package com.example.mytraveljournal.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _textAppName = MutableLiveData<String>().apply {
        value = ""
    }

    private val _textVersion = MutableLiveData<String>().apply {
        value = ""
    }
    private val _textDeveloper = MutableLiveData<String>().apply {
        value =""
    }

    private val _textCopyRight = MutableLiveData<String>().apply {
        value = ""
    }

    private val _imageLogo = MutableLiveData<Int>().apply {
        value = com.example.mytraveljournal.R.drawable.logo
    }
    val textAppName: LiveData<String> = _textAppName
    val textVersion: LiveData<String> = _textVersion
    val textDeveloper: LiveData<String> = _textDeveloper
    val textCopyRight: LiveData<String> = _textCopyRight
    val imageLogo: LiveData<Int> = _imageLogo

    fun setCopyRightText(text: String) {
        _textCopyRight.value = text
    }

    fun setDeveloperText(text: String) {
        _textDeveloper.value = text
    }

    fun setAppNameText(text: String) {
        _textAppName.value = text
    }

    fun setVersionText(text: String) {
        _textVersion.value = text
    }
}