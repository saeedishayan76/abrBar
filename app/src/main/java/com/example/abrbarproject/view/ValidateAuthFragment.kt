package com.example.abrbarproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abrbarproject.R
import com.example.abrbarproject.databinding.FragmentValidateAuthBinding
import com.example.abrbarproject.viewModel.AuthViewModel
import com.example.abrbarproject.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class ValidateAuthFragment : Fragment() {
    private lateinit var binding:FragmentValidateAuthBinding
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValidateAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnValidate.setOnClickListener {
            val inputtedCode = binding.edValidateCode.text.toString()
            val phoneNumber = authViewModel.getNumber().value
            Timber.d("number is ${phoneNumber}")
            if (!inputtedCode.isNullOrEmpty())
            {
                authViewModel.validateCode(phoneNumber!!,inputtedCode).observe(viewLifecycleOwner){
                    when (it) {
                        is Resource.Loading -> {
                            binding.progressLine.visibility = View.VISIBLE
                            Timber.d("loading")
                        }
                        is Resource.Success -> {
                            binding.progressLine.visibility = View.GONE
                            findNavController().navigate(R.id.action_validateAuthFragment_to_homeFragment)

                        }
                        is Resource.Error -> {
                            binding.progressLine.visibility = View.GONE

                            Timber.d("error ${it.message}")
                        }
                    }
                }
            }
        }

    }

}