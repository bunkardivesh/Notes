package com.divesh.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NoteRvAdapter.INoteRvAdapter {

    lateinit var viewModel: NoteViewModel
    private lateinit var etText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        etText = findViewById<EditText>(R.id.et_notes)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRvAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                adapter.updateData(it)
            }

        })


    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
    }

    fun submitText(view: View) {
        val text = etText.text.toString()
        if (text.isNotEmpty()){
            viewModel.insertNote(Note(text))
        }

    }
}