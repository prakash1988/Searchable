package com.example.searchable.util

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.searchable.data.UserDAO
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class UserProvider : ContentProvider() {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface UsersDaoEntryPoint {
        fun favDao(): UserDAO
    }

    private fun getUserDAO(context: Context): UserDAO {
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(context, UsersDaoEntryPoint::class.java)
        return hiltEntryPoint.favDao()
    }

    companion object {
        private const val AUTHORITY_URI = "com.example.searchable"
        private const val TABLE_NAME = "user_table"
        const val ID_FAVORITE_USER = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY_URI, TABLE_NAME, ID_FAVORITE_USER)
        }
    }


    override fun onCreate() = true


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        return if (code == ID_FAVORITE_USER) {
            val app = context?.applicationContext ?: throw IllegalStateException()
            val daos: UserDAO = getUserDAO(app)

            val cursor: Cursor? = if (code == ID_FAVORITE_USER) {
                daos.findAll()
            } else {
                return null
            }
            cursor?.setNotificationUri(app.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Uknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0
}