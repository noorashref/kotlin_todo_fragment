package com.example.todo

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.databinding.ItemLayoutBinding
import com.example.todo.model.TaskPresentation


/**
 Follows the adapter pattern it will modify the item_layout to
the data that you pass to it.
Recyclerview will work with 2 process
ViewCache
RecycledCache
 */
class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var dataSet = mutableListOf<TaskPresentation>()


    fun updataDataSet(newTask: TaskPresentation){
        dataSet.add(newTask)
        notifyDataSetChanged()
    }


    /**
    Holds a view reference of the @code {item_layout}
    It returns same reference just drawing data content.
     */
    class TaskViewHolder(itemView: ItemLayoutBinding)
        : RecyclerView.ViewHolder(itemView.root)


    /**
     * Create the current view holder for the adapter
      */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        // return TaskViewHolder(LAyoutInflater.infalter(
        // R.laout.item_layout
        // parent
        // false
        // ))

       return TaskViewHolder(ItemLayoutBinding.inflate(
           LayoutInflater.from(parent.context),parent,false
       ))
    }

    /**
     * Binds the data with viewholder reference
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_task_display).text = dataSet[position].todoName
            findViewById<TextView>(R.id.tv_category_display).text = dataSet[position].todoCategory
        }
//    holder.itemView.findViewById<TextView>(R.id.tv_task_display)
//        holder.itemView.findViewById<TextView>(R.id.tv_category_display)
    }

    /**
     * Return N size of Dataset
     * In java this method returns int and 0
     */
    override fun getItemCount(): Int {
        return dataSet.size
    }
}