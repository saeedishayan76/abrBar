package com.example.abrbarproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abrbarproject.R
import com.example.abrbarproject.databinding.FragmentAuthLoginBinding
import com.example.abrbarproject.viewModel.AuthViewModel
import com.example.abrbarproject.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthLoginFragment : Fragment() {
    private val authViewModel by activityViewModels<AuthViewModel>()
    private lateinit var binding: FragmentAuthLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {

            val insertedNumber = binding.edInputNumber.text.toString()
            if (!insertedNumber.isNullOrEmpty()) {
                authViewModel.authUser(insertedNumber).observe(viewLifecycleOwner)
                {
                    when (it) {
                        is Resource.Loading -> {
                            binding.progressLine.visibility = View.VISIBLE
                            Timber.d("loading")
                        }
                        is Resource.Success -> {
                            binding.progressLine.visibility = View.GONE

                            Timber.d("success ${it.data.code} ")
                            authViewModel.setNumber(insertedNumber)
                            findNavController().navigate(R.id.action_authLoginFragment_to_validateAuthFragment)
                        }
                        is Resource.Error -> {
                            binding.progressLine.visibility = View.GONE

                            Timber.d("error ${it.message}")
                        }
                    }
//        }
                }
            }

        }

    }
}