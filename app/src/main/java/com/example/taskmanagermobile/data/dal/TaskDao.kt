package com.example.taskmanagermobile.data.dal

import android.content.ContentValues
import android.content.Context
import com.example.taskmanagermobile.data.local.AppDatabase
import com.example.taskmanagermobile.data.model.Task

class TaskDao(context: Context) {
    private val appContext = context.applicationContext

    fun insert(task: Task) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("title", task.title)
                put("status", task.status)
                put("due_date", task.dueDate)
            }
            db.writableDatabase.insertOrThrow("tasks", null, values)
        }
    }

    fun list(): List<Task> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "tasks",
            arrayOf("id", "title", "status", "due_date"),
            null, null, null, null,
            "id DESC"
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(
                        Task(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                        )
                    )
                }
            }
        }
    }

    fun findById(id: Long): Task? = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "tasks",
            arrayOf("id", "title", "status", "due_date"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                Task(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
            } else null
        }
    }

    fun update(task: Task) {
        AppDatabase(appContext).use { db ->
            val values = ContentValues().apply {
                put("title", task.title)
                put("status", task.status)
                put("due_date", task.dueDate)
            }
            db.writableDatabase.update("tasks", values, "id = ?", arrayOf(task.id.toString()))
        }
    }

    fun delete(id: Long) {
        AppDatabase(appContext).use { db ->
            db.writableDatabase.delete("tasks", "id = ?", arrayOf(id.toString()))
        }
    }
}
