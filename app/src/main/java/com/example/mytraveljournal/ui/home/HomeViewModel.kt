package com.example.mytraveljournal.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytraveljournal.TravelMemory
import com.example.mytraveljournal.TravelMemoryAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeViewModel : ViewModel() {
    val memoriesAdapter = TravelMemoryAdapter(emptyList())

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    fun setText(text: String) {
        _text.value = text
    }

}