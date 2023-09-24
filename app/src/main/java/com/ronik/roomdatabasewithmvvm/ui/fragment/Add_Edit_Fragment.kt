package com.ronik.roomdatabasewithmvvm.ui.fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ronik.roomdatabasewithmvvm.R
import com.ronik.roomdatabasewithmvvm.UserViewModel
import com.ronik.roomdatabasewithmvvm.databinding.FragmentAddEditBinding
import com.ronik.roomdatabasewithmvvm.db.User
import com.ronik.roomdatabasewithmvvm.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Scope

@AndroidEntryPoint
class Add_Edit_Fragment : BaseFragment<FragmentAddEditBinding>() {


    private val userViewModel: UserViewModel by viewModels()
    val args: Add_Edit_FragmentArgs by navArgs()
    override val layoutResId: Int
        get() = R.layout.fragment_add__edit_

    override fun clickEvent() {

        binding.submitButton.setOnClickListener {
            if (isInputValid()) {
                // The input data is valid, proceed with submission.
                val name = binding.editTextName.text.toString()
                val email = binding.editTextEmail.text.toString()
                val phone = binding.editTextPhone.text.toString()


                args.user?.let {
                    lifecycleScope.launch {
                        userViewModel.updateUser(User(id=it.id,name=name, email = email, phoneNumber = phone))
                        findNavController().popBackStack()
                    }
                    Toast.makeText(requireContext(), "User Updated successfully", Toast.LENGTH_SHORT).show()

                }?:run {
                    lifecycleScope.launch {
                        userViewModel.inserUser(User(name=name, email = email, phoneNumber = phone))
                    }
                    Toast.makeText(requireContext(), "User inserted successfully", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }


            }
        }

    }

    override fun initView() {
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this
//        val args = Add_Edit_FragmentArgs.fromBundle(requireArguments())
        val userData = args.user


        // Retrieve title and data arguments
        val title = arguments?.getString("title") ?: ""
        (activity as AppCompatActivity).supportActionBar?.title = title

        // Pre-fill data if it's an edit operation
        userData?.let {
            // Fill your form fields with the data from 'userData'
            binding.editTextName.setText(userData.name)
            binding.editTextEmail.setText(userData.email)
            binding.editTextPhone.setText(userData.phoneNumber)
        }

    }

    private fun isInputValid(): Boolean {
        val name = binding.editTextName.text.toString().trim()
        val email =  binding.editTextEmail.text.toString().trim()
        val phone =  binding.editTextPhone.text.toString().trim()

        if (name.isEmpty()) {
            binding.editTextName.error = "Name is required"
            return false
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding. editTextEmail.error = "Valid email address is required"
            return false
        }

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            binding.editTextPhone.error = "Valid phone number is required"
            return false
        }

        // All input data is valid.
        return true
    }



}