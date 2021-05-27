package com.example.searchable.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
        val name: String,
        val image: String,
        val desc: String
){
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}