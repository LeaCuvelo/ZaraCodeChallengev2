package com.zara.cuvelo.codechallenge.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zara.cuvelo.codechallenge.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        //TODO add Splash here

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.lifecycleOwner = this

    }


}