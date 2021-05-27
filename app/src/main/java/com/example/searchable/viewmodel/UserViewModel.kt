package com.example.searchable.viewmodel

import androidx.lifecycle.ViewModel
import com.example.searchable.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val userRepo: UserRepo): ViewModel(){

    val allUsers= userRepo.users








}