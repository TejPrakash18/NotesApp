package com.tejdev.notesapp.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tejdev.notesapp.Data.Entity.Note
import com.tejdev.notesapp.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NoteAdapter(private val notes: List<Note>, private val listener: onNoteClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    interface onNoteClickListener {
        fun onNoteClick(note: Note)
        fun onNoteLongClick(note: Note)
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root),
        Parcelable {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val note = notes[position]
                        listener.onNoteClick(note)
                    }
                }
                root.setOnLongClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val note = notes[position]
                        listener.onNoteLongClick(note)
                    }
                    true
                }
            }
        }

        constructor(parcel: Parcel) : this(TODO("binding")) {
        }

        fun bind(note: Note) {
            binding.apply {
                titleNote.text = note.title
                contentNote.text = note.content
                val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                dateNote.text = formattedDate.format(note.date)
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {

        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ViewHolder> {
            override fun createFromParcel(parcel: Parcel): ViewHolder {
                return ViewHolder(parcel)
            }

            override fun newArray(size: Int): Array<ViewHolder?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
