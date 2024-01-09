package com.example.mytraveljournal

import android.widget.Button

data class TravelMemory(
    var placeName: String,
    var placeLocation: String,
    var dateOfTravel: String,
    var travelType: String,
    var travelMood: Int,
    var travelNotes: String,
    var isFavorite: Boolean,
    var detailsButton: Button?,
    var editButton: Button?
)
