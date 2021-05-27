package com.example.searchable.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchable.databinding.AddUserBinding
import com.example.searchable.viewmodel.AddUserViewmodel
import com.example.searchable.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment(){
    private val addUserViewmodel : AddUserViewmodel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AddUserBinding.inflate(inflater)
        binding.imgUrl.setText("https://avatarfiles.alphacoders.com/143/143639.jpg")
        binding.save.setOnClickListener {
            validateUserData(binding.name.text.toString(),binding.imgUrl.text.toString(),binding.desc.text.toString())
        }
        return binding.root
    }

    private fun validateUserData(name:String, image : String, desc : String){
        when {
            TextUtils.isEmpty(name) -> {
                Toast.makeText(activity,"Please enter Name",Toast.LENGTH_LONG).show()
            }
            TextUtils.isEmpty(desc) -> {
                Toast.makeText(activity,"Please enter Desc",Toast.LENGTH_LONG).show()
            }
            else -> {
                addUserViewmodel.insertUser(name,image, desc)
                activity?.onBackPressed()
            }
        }
    }

}