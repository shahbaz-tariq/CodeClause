package com.example.todolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.Todo

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        holder.cbCompleted.setOnCheckedChangeListener { _, isChecked ->
            current.completed = isChecked
            holder.bind(current)
        }

    }

    fun getTodo(position: Int): Todo {
        return getItem(position)
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val desc: TextView = itemView.findViewById(R.id.tvDesc)
        val cbCompleted: CheckBox = itemView.findViewById(R.id.cbCompleted)

        fun bind(todo: Todo) {
            title.text = todo.name
            desc.text = todo.desc

            // Apply strikethrough effect if the item is completed
            if (todo.completed) {
                title.paintFlags = title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                desc.paintFlags = title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                desc.paintFlags = title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            cbCompleted.isChecked = todo.completed
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.each_rv, parent, false)
                return TodoViewHolder(view)
            }
        }
    }

    class TodoComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
