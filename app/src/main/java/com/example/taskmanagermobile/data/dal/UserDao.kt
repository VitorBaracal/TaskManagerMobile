package com.example.taskmanagermobile.data.dal

import android.content.ContentValues
import android.content.Context
import com.example.taskmanagermobile.data.local.AppDatabase
import com.example.taskmanagermobile.data.model.User

class SavedPassword(val hash: ByteArray, val salt: ByteArray, val userId: Long)

class UserDao(context: Context) {
    private val appContext = context.applicationContext

    fun register(name: String, email: String, password: ByteArray, salt: ByteArray) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("name", name)
                put("email", email)
                put("password", password)
                put("salt", salt)
            }
            db.writableDatabase.insertOrThrow("users", null, values)
        }
    }

    fun findPassword(email: String): SavedPassword? = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "users",
            arrayOf("password", "salt", "id"),
            "email = ?",
            arrayOf(email),
            null, null, null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                SavedPassword(cursor.getBlob(0), cursor.getBlob(1), cursor.getLong(2))
            } else null
        }
    }

    fun list(): List<User> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "users",
            arrayOf("id", "name", "email"),
            null, null, null, null,
            "name COLLATE NOCASE"
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(User(cursor.getLong(0), cursor.getString(1), cursor.getString(2)))
                }
            }
        }
    }

    fun findById(id: Long): User? = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "users",
            arrayOf("id", "name", "email", "photo"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
            } else null
        }
    }

    fun update(id: Long, name: String, hash: ByteArray? = null, salt: ByteArray? = null) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("name", name)
                if (hash != null) put("password", hash)
                if (salt != null) put("salt", salt)
            }
            db.writableDatabase.update("users", values, "id = ?", arrayOf(id.toString()))
        }
    }

    fun updatePhoto(id: Long, photo: String) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("photo", photo)
            }
            db.writableDatabase.update("users", values, "id = ?", arrayOf(id.toString()))
        }
    }

    fun delete(id: Long) {
        AppDatabase(appContext).use { db ->
            db.writableDatabase.delete("users", "id = ?", arrayOf(id.toString()))
        }
    }
}
