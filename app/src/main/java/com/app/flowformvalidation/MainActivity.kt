package com.app.flowformvalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.app.flowformvalidation.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initListener()
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value->
                binding.submitButton.isEnabled = value
            }
        }
    }

    private fun initListener() {
        binding.nameEt.addTextChangedListener {
            viewModel.setFirstName(it.toString())
        }

        binding.passwordEt.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }

        binding.userIdEt.addTextChangedListener {
            viewModel.setUserId(it.toString())
        }
    }
}