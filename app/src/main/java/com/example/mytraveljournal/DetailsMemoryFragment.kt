package com.example.mytraveljournal

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytraveljournal.databinding.FragmentDetailsMemoryBinding
import com.example.mytraveljournal.ui.profile.ProfileViewModel
import java.util.Locale

class DetailsMemoryFragment : Fragment() {
    private var _binding: FragmentDetailsMemoryBinding? = null
    private lateinit var sharedViewModel: SharedViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailsViewModel =
            ViewModelProvider(this).get(SharedViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        _binding = FragmentDetailsMemoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.d("ViewModel", "SharedViewModel created in DetailsMemoryFragment: $sharedViewModel")

        _binding?.viewModel = sharedViewModel
        _binding?.lifecycleOwner = viewLifecycleOwner

        val textViewPlaceName: TextView = binding.detailsPlaceName
        val textViewPlaceLocation: TextView = binding.detailsLocation
        val textViewTravelType: TextView = binding.detailsTravelType
        val textViewTravelMood: TextView = binding.detailsTravelMood
        val textViewTravelNotes: TextView = binding.detailsTravelNotes
        val textViewDate: TextView = binding.detailsDateOfTravel

        Log.d("placeName", sharedViewModel.placeName.value.toString())


        sharedViewModel.placeName.observe(viewLifecycleOwner) {
            textViewPlaceName.setText(it)
            Log.d("placeName", it)
        }
        sharedViewModel.placeLocation.observe(viewLifecycleOwner) {
            textViewPlaceLocation.setText(it)
        }
        sharedViewModel.travelType.observe(viewLifecycleOwner) {
            textViewTravelType.setText(it)
        }
        sharedViewModel.travelMood.observe(viewLifecycleOwner) {
            textViewTravelMood.setText(it.toString())
        }
        sharedViewModel.travelNotes.observe(viewLifecycleOwner) {
            textViewTravelNotes.setText(it)
        }

        sharedViewModel.dateOfTravel.observe(viewLifecycleOwner) { calendar ->
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar?.time)
            textViewDate.text = formattedDate
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}