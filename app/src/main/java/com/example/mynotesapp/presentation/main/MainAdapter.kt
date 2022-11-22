package com.example.mynotesapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.ListItemMainBinding
import com.example.mynotesapp.domain.note.entity.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    private var listData = ArrayList<NoteEntity>()
    var onLongItemClick: ((NoteEntity) -> Unit)? = null
    var onItemClick: ((NoteEntity, Int) -> Unit)? = null

    fun setData(newData: List<NoteEntity>?) {
        if (newData == null) return
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemMainBinding.bind(itemView)
        fun bind(data: NoteEntity) {
            val convertTimestamp = SimpleDateFormat("dd MMMM yyyy").format(Date(data.timestamp))
            val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())

            val time = if (currentDate == convertTimestamp) {
                SimpleDateFormat("hh:mm aaa").format(Date(data.timestamp))
            } else {
                SimpleDateFormat("dd MMMM yyyy").format(Date(data.timestamp))
            }

            with(binding) {
                tvContent.text = data.content
                tvTitle.text = data.title
                tvTimestamp.text = time
            }
        }

        init {
            binding.root.setOnLongClickListener {
                onLongItemClick?.invoke(listData[bindingAdapterPosition])
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition], bindingAdapterPosition)
                return@setOnClickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}