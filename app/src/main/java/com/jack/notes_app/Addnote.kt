package com.jack.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.jack.notes_app.databinding.ActivityAddnoteBinding

class Addnote : AppCompatActivity() {
    private lateinit var binding: ActivityAddnoteBinding
    private lateinit var db:NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=(NotesDatabaseHelper(this))

        binding.savebutton.setOnClickListener {
            val tittle =binding.tittleedittext.text.toString()
            val content=binding.contentedittext.text.toString()
            val note = Notes(0,tittle,content)
            db.insertnote(note)
            db.close()
            Toast.makeText(this,"Notes Saved",Toast.LENGTH_SHORT).show()
        }
    }
}