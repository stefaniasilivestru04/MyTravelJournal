package com.example.mytraveljournal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SharedViewModel: ViewModel() {
    private val _travelMemories = MutableLiveData<List<TravelMemory>>().apply { value = emptyList() }
    val travelMemories: MutableLiveData<List<TravelMemory>> get() = _travelMemories
    private var editMemory: TravelMemory? = null

    val placeName = MutableLiveData<String>()
    val placeLocation = MutableLiveData<String>()
    val dateOfTravel = MutableLiveData<Calendar>()
    val travelType = MutableLiveData<String>()
    val travelMood = MutableLiveData<Int>()
    val travelNotes = MutableLiveData<String>()
    val isFavorite = MutableLiveData<Boolean>()

    fun setEditMemory(memory: TravelMemory) {
        editMemory = memory
    }

    fun getMemoryToEditByPlaceName(placeName: String): TravelMemory? {
        return _travelMemories.value?.find { it.placeName == placeName }
    }

    fun addMemory() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateFormatted = dateOfTravel.value?.let { dateFormat.format(it.time) } ?: ""

        val newMemory = TravelMemory(
            placeName = placeName.value ?: "",
            placeLocation = placeLocation.value ?: "",
            dateOfTravel = dateFormatted,
            travelType = travelType.value ?: "",
            travelMood = travelMood.value ?: 0,
            travelNotes = travelNotes.value ?: "",
            isFavorite = isFavorite.value ?: false,
            detailsButton = null,
            editButton = null
        )

        val updatedMemories = _travelMemories.value?.toMutableList() ?: mutableListOf()
        updatedMemories.add(newMemory)
        _travelMemories.value = updatedMemories
        Log.d("addMemory", updatedMemories.toString())
        Log.d("addMemory", _travelMemories.value.toString())
        Log.d("save2", "Memory saved: $newMemory")
    }

    fun editMemory(editedMemory: TravelMemory) {
        editMemory?.let {
            val currentMemories = _travelMemories.value?.toMutableList() ?: mutableListOf()
            val index = currentMemories.indexOf(it)
            if (index != -1) {
                currentMemories[index] = editedMemory
                _travelMemories.value = currentMemories
            }
        }
    }

    fun onTravelTypeSelected(selectedItem: String) {
        travelType.value = selectedItem;
    }

    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val selectedCalendar = Calendar.getInstance()
        selectedCalendar.set(year, monthOfYear, dayOfMonth)
        dateOfTravel.value = selectedCalendar
    }
}