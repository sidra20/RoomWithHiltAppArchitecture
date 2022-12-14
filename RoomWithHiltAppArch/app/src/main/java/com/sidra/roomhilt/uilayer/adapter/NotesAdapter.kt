package com.sidra.roomhilt.uilayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sidra.roomhilt.R
import com.sidra.roomhilt.databinding.NoteItemBinding
import com.sidra.roomhilt.datalayer.Note
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotesAdapter @Inject constructor(private val context: Context, private val listener : Listener) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    var noteList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = NoteItemBinding.inflate(inflater,parent,false)
        val obj = MyViewHolder(binding, listener)
        return obj

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notes = noteList[position]
        holder.binding.note=notes

        holder.itemView.setOnClickListener {
            listener.itemClick(notes)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateList(newList : List<Note>)
    {
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: NoteItemBinding, val listener: Listener):RecyclerView.ViewHolder(binding.root)
    {

    }
    interface Listener{
        fun itemClick(note: Note)
    }
}