package com.jack.notes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.notes_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var db:NotesDatabaseHelper
    private lateinit var NotesAdapter:NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NotesDatabaseHelper(this)
        NotesAdapter= NotesAdapter(db.getallnotes(),this)
        binding.notesrecycleview.layoutManager=LinearLayoutManager(this)
        binding.notesrecycleview.adapter=NotesAdapter
        binding.addbutton.setOnClickListener {
            val intent=Intent(this,Addnote::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        NotesAdapter.refresh(db.getallnotes())
    }
}