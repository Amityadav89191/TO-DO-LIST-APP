package com.jack.notes_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Notes>,context: Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    val db:NotesDatabaseHelper= NotesDatabaseHelper(context)

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tittletextview:TextView=itemView.findViewById(R.id.tittleTextview)
        val describtion:TextView=itemView.findViewById(R.id.contenttextview)
         val updatebtnn:ImageView=itemView.findViewById(R.id.edtbtn)
         val deletbtn:ImageView=itemView.findViewById(R.id.delted)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int=notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]
        holder.tittletextview.text=note.tittle
        holder.describtion.text=note.desc

        holder.updatebtnn.setOnClickListener {
            val intent=Intent(holder.itemView.context,Update_Activity::class.java).apply {
                putExtra("notes id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deletbtn.setOnClickListener {
            db.deletd(note.id)
            refresh(db.getallnotes())
            Toast.makeText(holder.itemView.context,"Deled ",Toast.LENGTH_SHORT).show()
        }

    }
    fun refresh(newnotes:List<Notes>){
        notes=newnotes
        notifyDataSetChanged()
    }

}