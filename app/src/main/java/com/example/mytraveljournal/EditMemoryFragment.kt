package com.example.mytraveljournal

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytraveljournal.databinding.FragmentAddMemoryBinding
import com.example.mytraveljournal.databinding.FragmentEditMemoryBinding
import java.util.Locale

class EditMemoryFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private var _binding: FragmentEditMemoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditMemoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val placeName: String? = sharedViewModel.placeName.value
        Log.d("placeName", placeName.toString())
        val memoryToEdit = sharedViewModel.getMemoryToEditByPlaceName(placeName.toString())

        binding.buttonEditMemory.setOnClickListener {
            if (memoryToEdit != null) {
                memoryToEdit.placeName = binding.editPlaceName.text.toString()
                memoryToEdit.placeLocation = binding.editPlaceLocation.text.toString()
                memoryToEdit.travelType = binding.spinnerTravelType.selectedItem.toString()
                memoryToEdit.travelMood = binding.seekBarTravelMood.progress
                memoryToEdit.travelNotes = binding.editTravelNotes.text.toString()

                val selectedYear = binding.datePicker.year
                val selectedMonth = binding.datePicker.month
                val selectedDay = binding.datePicker.dayOfMonth

                memoryToEdit.dateOfTravel = formatDate(selectedYear, selectedMonth, selectedDay)
                sharedViewModel.editMemory(memoryToEdit)
                Toast.makeText(context, "Memory edited", Toast.LENGTH_SHORT).show()
            }

            findNavController().navigate(R.id.action_nav_add_memory_to_nav_home)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun formatDate(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        val selectedCalendar = Calendar.getInstance()
        selectedCalendar.set(year, monthOfYear, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(selectedCalendar.time)
    }
}