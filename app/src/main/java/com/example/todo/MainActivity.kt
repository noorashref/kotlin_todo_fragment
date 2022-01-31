package com.example.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.model.TaskPresentation

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CreateTodoFragment.CreateTodo {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inflateCreateTodoFragment()
    }
    override fun createNewTodo(todo: TaskPresentation) {
        Log.d(TAG, "createNewTodo: $todo")
        supportFragmentManager.findFragmentById(R.id.display_fragment)?.let {
            (it as DisplayFragment).updateAdapter(todo)
        }
    }

//    Create a dynamic fragment using support fragment manager

    private fun inflateCreateTodoFragment() {

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.add_fragment, CreateTodoFragment())
        transaction.commit()

    }
}