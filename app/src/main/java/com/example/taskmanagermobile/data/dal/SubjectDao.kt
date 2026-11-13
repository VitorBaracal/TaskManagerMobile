package com.example.taskmanagermobile.data.dal

import android.content.ContentValues
import android.content.Context
import com.example.taskmanagermobile.data.local.AppDatabase
import com.example.taskmanagermobile.data.model.Subject

class SubjectDao(context: Context) {
    private val appContext = context.applicationContext

    fun insert(subject: Subject) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("name", subject.name)
                put("teacher", subject.teacher)
            }
            db.writableDatabase.insertOrThrow("subjects", null, values)
        }
    }

    fun list(): List<Subject> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "subjects",
            arrayOf("id", "name", "teacher"),
            null, null, null, null,
            "name COLLATE NOCASE"
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(Subject(cursor.getLong(0), cursor.getString(1), cursor.getString(2)))
                }
            }
        }
    }

    fun findById(id: Long): Subject? = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "subjects",
            arrayOf("id", "name", "teacher"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                Subject(cursor.getLong(0), cursor.getString(1), cursor.getString(2))
            } else null
        }
    }

    fun update(subject: Subject) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("name", subject.name)
                put("teacher", subject.teacher)
            }
            db.writableDatabase.update("subjects", values, "id = ?", arrayOf(subject.id.toString()))
        }
    }

    fun delete(id: Long) {
        AppDatabase(appContext).use { db ->
            db.writableDatabase.delete("subjects", "id = ?", arrayOf(id.toString()))
        }
    }
}
