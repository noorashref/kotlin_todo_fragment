package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todo.databinding.DisplayFragmentBinding
import com.example.todo.databinding.ItemLayoutBinding
import com.example.todo.model.TaskPresentation

class DisplayFragment : Fragment() {

    private val adapter : TaskAdapter by lazy {
        createAdapter()
    }

    private lateinit var binding: DisplayFragmentBinding

    private fun createAdapter() :TaskAdapter {
        return TaskAdapter()
    }

    fun updateAdapter(newTodo: TaskPresentation) {

        adapter.updataDataSet(newTodo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentBinding.inflate(inflater,container,false)

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val layoutManagerLinear  = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val layoutGrid = GridLayoutManager(requireContext(),3)
        val staggered = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        binding.listTodo.adapter = adapter
        binding.listTodo.layoutManager = layoutGrid

    }
}

/*Steps for recycler view
1 - Have a data structure (TaskPresentation)
2 - Create your item_layout
3 - Create a subclass of RecyclerView.Adapter
4 - Create a subclass of RecyclerView.ViewHolder
5 - Create the layout manager (Linear, Grid, Staggered )
*/
