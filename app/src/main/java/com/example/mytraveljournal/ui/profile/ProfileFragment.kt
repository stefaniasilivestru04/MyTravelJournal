package com.example.mytraveljournal.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytraveljournal.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewAppName: TextView = binding.textAppName
        val textViewVersion: TextView = binding.textVersion
        val textViewDeveloper: TextView = binding.textDeveloper
        val textViewCopyright: TextView = binding.textCopyright
        val imageViewLogo: ImageView = binding.imageLogo

        profileViewModel.setAppNameText(resources.getString(com.example.mytraveljournal.R.string.profile_app_name))
        profileViewModel.setVersionText(resources.getString(com.example.mytraveljournal.R.string.profile_app_version))
        profileViewModel.setDeveloperText(resources.getString(com.example.mytraveljournal.R.string.profile_app_developer))
        profileViewModel.setCopyRightText(resources.getString(com.example.mytraveljournal.R.string.profile_app_copyright))

        profileViewModel.textAppName.observe(viewLifecycleOwner) {
            textViewAppName.text = it
        }
        profileViewModel.textVersion.observe(viewLifecycleOwner) {
            textViewVersion.text = it
        }
        profileViewModel.textDeveloper.observe(viewLifecycleOwner) {
            textViewDeveloper.text = it
        }

        profileViewModel.textCopyRight.observe(viewLifecycleOwner) {
            textViewCopyright.text = it
        }
        profileViewModel.imageLogo.observe(viewLifecycleOwner) {
            imageViewLogo.setImageResource(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}