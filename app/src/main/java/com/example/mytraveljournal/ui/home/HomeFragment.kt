package com.example.mytraveljournal.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytraveljournal.AddMemoryFragment
import com.example.mytraveljournal.R
import com.example.mytraveljournal.SharedViewModel
import com.example.mytraveljournal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textAddMemory

        homeViewModel.setText(resources.getString(R.string.text_add_memory))
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_add_memory)
        }

        // Enable recycler view
        val recyclerView = binding.recyclerView
        recyclerView.adapter = homeViewModel.memoriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sharedViewModel.travelMemories.observe(viewLifecycleOwner) { memories ->
            homeViewModel.memoriesAdapter.updateData(memories)
            Log.d("observe", memories.toString())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}