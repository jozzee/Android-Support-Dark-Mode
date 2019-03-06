package com.jozzee.android.research.multipletheme


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.edit
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreference = context!!.getSharedPreferences(PREFERENCES_NAME_KEY, Context.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Update is checked on switch from hared preferences.
        switch_night_mode.isChecked = sharedPreference.getBoolean(DARK_MODE_KEY, false)

        switch_night_mode.setOnCheckedChangeListener { switch, isChecked ->
            //Set is open dark mode to Shared preferences and recreate activity.
            sharedPreference.edit {
                putBoolean(DARK_MODE_KEY, isChecked)
            }
            activity?.recreate()
        }
    }
}
