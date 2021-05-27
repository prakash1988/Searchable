package com.example.searchable.viewmodel

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchable.data.User
import com.example.searchable.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewmodel @Inject constructor(val userRepo: UserRepo): ViewModel(){

    fun insertUser(name: String,image : String,desc :String){
        viewModelScope.launch {
           val user = User(name,image,desc)
            userRepo.insertUser(user)
        }


    }
}