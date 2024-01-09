package com.example.mytraveljournal.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytraveljournal.LanguageApplication
import com.example.mytraveljournal.databinding.FragmentSettingsBinding
import com.example.mytraveljournal.ui.share.ShareViewModel
import java.util.Locale

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textViewTheme: TextView = binding.textChangeTheme
        val buttonChangeTheme : Button = binding.btnChangeTheme
        val textViewLanguage : TextView = binding.textChangeLanguage
        val spinnerLanguage : Spinner = binding.spinnerLanguage
        val languages = resources.getStringArray(com.example.mytraveljournal.R.array.languages)

        // Handle selected language
        spinnerLanguage.setSelection(languages.indexOf(LanguageApplication.selectedLanguage))
        spinnerLanguage.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) { // Change the app language based on the selected language
                if (languages != null && position >= 0 && position < languages.size) {
                    setAppLanguage(languages[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                   // Do nothing
            }

        }

        // Handle change theme
        buttonChangeTheme.setOnClickListener {
            toggleDarkTheme()
        }

        // Set the text for the views
        settingsViewModel.setThemeText(resources.getString(com.example.mytraveljournal.R.string.settings_change_theme))
        settingsViewModel.setLanguageText(resources.getString(com.example.mytraveljournal.R.string.settings_change_language))

        settingsViewModel.textTheme.observe(viewLifecycleOwner) {
            textViewTheme.text = it
        }
        settingsViewModel.textLanguage.observe(viewLifecycleOwner) {
            textViewLanguage.text = it
        }

        return root
    }


    private fun toggleDarkTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isDarkTheme = !sharedPreferences.getBoolean("theme_switch", false)
        sharedPreferences.edit().putBoolean("theme_switch", isDarkTheme).apply()

        // Apply the selected theme dynamically
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Restart the activity to apply the theme changes
        requireActivity().recreate()
    }

    fun setAppLanguage(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)

        val editor = PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()
        editor.putString("language", language)
        editor.apply()
        LanguageApplication.selectedLanguage = language
        resources.updateConfiguration(config, resources.displayMetrics)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}