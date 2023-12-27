package com.example.todolist

import android.app.Application
import com.example.todolist.data.TodoDatabase
import com.example.todolist.data.TodoRepo

class TodoApplication: Application() {

    val database by lazy { TodoDatabase.getDatabase(this) }
    val repository by lazy { TodoRepo(database.todoDao()) }
}