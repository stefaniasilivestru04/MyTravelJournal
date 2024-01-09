package com.example.mytraveljournal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytraveljournal.databinding.FragmentAddMemoryBinding

class AddMemoryFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private var _binding: FragmentAddMemoryBinding? = null
    private val binding get() = _binding!!

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Gallery photo selected, handle the result here
                val selectedImageUri = result.data?.data
                // Do something with the selected image URI, e.g., display it in an ImageView
                Log.d("Gallery", "Selected Image URI: $selectedImageUri")
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentAddMemoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        Log.d("ViewModel", "SharedViewModel created in AddFragment: $sharedViewModel")

        binding.buttonSaveMemory.setOnClickListener {
            Log.d("button", "Save button clicked")
            sharedViewModel.placeName.value = binding.editPlaceName.text.toString()
            sharedViewModel.placeLocation.value = binding.editPlaceLocation.text.toString()
            sharedViewModel.travelType.value = binding.spinnerTravelType.selectedItem.toString()
            sharedViewModel.travelMood.value = binding.seekBarTravelMood.progress
            sharedViewModel.travelNotes.value = binding.editTravelNotes.text.toString()


            // Get the selected date from the DatePicker
            val selectedYear = binding.datePicker.year
            val selectedMonth = binding.datePicker.month
            val selectedDay = binding.datePicker.dayOfMonth

            sharedViewModel.onDateSelected(selectedYear, selectedMonth, selectedDay)
            sharedViewModel.addMemory()
            findNavController().navigate(R.id.action_nav_add_memory_to_nav_home)

        }

        binding.buttonAddPhotos.setOnClickListener {
            Log.d("button", "Add Photos button clicked")
            addPhotosFromGallery()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
           _binding = null
    }

    fun addPhotosFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(galleryIntent)
    }
}