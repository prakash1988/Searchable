package com.example.searchable.repo

import com.example.searchable.data.User
import com.example.searchable.data.UserDAO
import javax.inject.Inject

class UserRepo @Inject constructor(private val dao:UserDAO){

    val users = dao.getAllUsers()

    suspend fun insertUser(user: User) =
        dao.insertUser(user)

}