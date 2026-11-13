package com.example.taskmanagermobile.data.dal

import android.content.ContentValues
import android.content.Context
import com.example.taskmanagermobile.data.local.AppDatabase
import com.example.taskmanagermobile.data.model.Note

class NoteDao(context: Context) {
    private val appContext = context.applicationContext

    fun insert(note: Note) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("title", note.title)
                put("content", note.content)
            }
            db.writableDatabase.insertOrThrow("notes", null, values)
        }
    }

    fun list(): List<Note> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "notes",
            arrayOf("id", "title", "content"),
            null, null, null, null,
            "id DESC"
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2)))
                }
            }
        }
    }

    fun findById(id: Long): Note? = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "notes",
            arrayOf("id", "title", "content"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2))
            } else null
        }
    }

    fun update(note: Note) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("title", note.title)
                put("content", note.content)
            }
            db.writableDatabase.update("notes", values, "id = ?", arrayOf(note.id.toString()))
        }
    }

    fun delete(id: Long) {
        AppDatabase(appContext).use { db ->
            db.writableDatabase.delete("notes", "id = ?", arrayOf(id.toString()))
        }
    }
}
