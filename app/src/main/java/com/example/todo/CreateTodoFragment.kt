package com.example.todo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todo.databinding.CreateTodoBinding
import com.example.todo.model.TaskPresentation

class CreateTodoFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: CreateTodoBinding  //create_todo.xml + Binding
    private val adapter: ArrayAdapter<String> by lazy {
        createAdapter() //for casting use operator 'as'
    }

    //private val adapter2 = createAdapter()
    private var selectedCategory: String? = null

    interface CreateTodo {
        fun createNewTodo(todo: TaskPresentation)
    }

    private lateinit var listener: CreateTodo

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is CreateTodo

            -> listener = context
            else -> throw Exception("Incorrect host Activity")
        }
    }


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CreateTodoBinding.inflate(inflater, container, false)

        binding.ibSaveTask.setOnClickListener(this)
        binding.spCategory.adapter = adapter

        //View view = inflater.inflate(R.layout.create,container,false)
        //view.findViewById(R.id...).setOn
        //return view
        binding.spCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedCategory = adapter.getItem(p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        return binding.root
    }

    private fun createAdapter(): ArrayAdapter<String> {
        return ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.categories)
        )
    }

    override fun onClick(p0: View?) {

//        if(binding.tilTodoTask.editText!!.text.isNotEmpty() && selectedCategory != null){
//
//        }else{
//
//        }

        binding.tilTodoTask.editText?.let { todoTask ->
            selectedCategory?.let { category ->
                if (todoTask.text.isNotEmpty() && category != null)
                    updateDisplayFragment(todoTask.text.toString(), category)
            }
        } ?: Toast.makeText(context, "No empty value", Toast.LENGTH_LONG).show()
    }

    private fun updateDisplayFragment(todoTask: String, selectedCategory: String) {

        listener.createNewTodo(
            TaskPresentation(todoTask, selectedCategory)
        )

    }

}