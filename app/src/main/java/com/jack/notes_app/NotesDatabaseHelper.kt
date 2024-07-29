package com.jack.notes_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.core.content.contentValuesOf

class NotesDatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION)

    { companion object{
        private const val DATABASE_NAME="notesapp"
        private const val  DATABASE_VERSION=1
        private const val TABLE_NAME="allnotes"
        private const val COLUMN_ID="id"
        private const val COLUMN_TITTLE="tittle"
        private const val COLUMN_CONTENT="content"
    }

        override fun onCreate(db: SQLiteDatabase?) {
            val createTableQuery="CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITTLE TEXT,$COLUMN_CONTENT TEXT)"
            db?.execSQL(createTableQuery)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
             val dropTableQuerry="DROP TABLE IF EXISTS $TABLE_NAME"
            db?.execSQL(dropTableQuerry)
            onCreate(db)
        }
        fun insertnote(notes: Notes){
            val db=writableDatabase
            val values= contentValuesOf().apply {
                put(COLUMN_TITTLE,notes.tittle)
                put(COLUMN_CONTENT,notes.desc)
            }
            db.insert(TABLE_NAME,null,values)
            db.close()
        }
        fun getallnotes():List<Notes> {
            val notelist= mutableListOf<Notes>()
            val db=readableDatabase
            val query=" SELECT * FROM $TABLE_NAME"
            val cursor=db.rawQuery(query,null)

            while(cursor.moveToNext()){
                val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val tittle=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITTLE))
                val des=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

                val note= Notes(id,tittle,des)
                notelist.add(note)

            }
            cursor.close()
            db.close()
            return notelist

        }

        fun updatenote(note:Notes){
            val db=writableDatabase
            val values=ContentValues().apply {
                put(COLUMN_TITTLE,note.tittle)
                put(COLUMN_CONTENT,note.desc)
            }
            val WhereClause="$COLUMN_ID= ?"
            val Wherearg= arrayOf(note.id.toString())
            db.update(TABLE_NAME,values,WhereClause,Wherearg)
            db.close()
        }

        fun getNoteId(Noteid:Int):Notes{
            val db=readableDatabase
            val query="SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=id"
            val cursor=db.rawQuery(query,null)
            cursor.moveToFirst()

            val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val tittle=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITTLE))
            val des=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            cursor.close()
            db.close()
            return Notes(id,tittle,des)
        }
        fun deletd(Noteid: Int){
            val db=readableDatabase
            val WhereClause="$COLUMN_ID= ?"
            val Wherearg= arrayOf(Noteid.toString())
            db.delete(TABLE_NAME,WhereClause,Wherearg)
            db.close()
        }
    }