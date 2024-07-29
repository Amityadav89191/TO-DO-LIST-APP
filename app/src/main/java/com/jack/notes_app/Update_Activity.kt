package com.jack.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jack.notes_app.databinding.ActivityUpdateBinding

class Update_Activity : AppCompatActivity() {
  private  lateinit var binding: ActivityUpdateBinding
    private  lateinit var db:NotesDatabaseHelper
    private  var noteid:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NotesDatabaseHelper(this)

        noteid=intent.getIntExtra("notes id",-1)

        if(noteid==-1){
            finish()
            return
        }
        val note=db.getNoteId(noteid)
        binding.updatetittleedittext.setText(note.tittle)
        binding.updatecontentedittext.setText(note.desc)

        binding.updatesavebutton.setOnClickListener {
            val newtittle=binding.updatetittleedittext.text.toString()
            val newcdes=binding.updatecontentedittext.text.toString()

            val updatenote=Notes(noteid,newtittle,newcdes)

            db.updatenote(updatenote)
            finish()
            Toast.makeText(this,"Changed Saved",Toast.LENGTH_SHORT).show()
        }

    }
}