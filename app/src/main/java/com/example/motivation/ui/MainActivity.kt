package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    private var filter: Int = MotivationConstants.PHRASEFILTER.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide navigation
        supportActionBar?.hide()

        securityPreferences = SecurityPreferences(this)

        setListeners()

        handleFilter(R.id.image_all)
        refreshPhrase()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        showUserName()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setListeners() { //events
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.image_all,
            R.id.image_happy,
            R.id.image_sunny
        )
        if( id in listId){
            handleFilter(id)
        } else if (id == R.id.button_new_phrase) {
            refreshPhrase()
        } else if (id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun refreshPhrase() {
        binding.textPhrase.text = mock.getPhrase(filter)
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                filter = MotivationConstants.PHRASEFILTER.ALL
                binding.imageAll.setColorFilter(
                    ContextCompat.getColor(this, R.color.white))

            }
            R.id.image_happy -> {
                filter = MotivationConstants.PHRASEFILTER.HAPPY
                binding.imageHappy.setColorFilter(
                    ContextCompat.getColor(
                    this, R.color.white))

            }
            R.id.image_sunny -> {
                filter= MotivationConstants.PHRASEFILTER.SUNNY
                binding.imageSunny.setColorFilter(
                    ContextCompat.getColor(this, R.color.white))

            }
        }
    }

    private fun showUserName(){
        val name = securityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}