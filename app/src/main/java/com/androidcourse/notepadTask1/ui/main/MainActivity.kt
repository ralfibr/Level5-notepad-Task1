package com.androidcourse.notepadTask1.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidcourse.notepadTask1.R
import com.androidcourse.notepadTask1.ui.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * @author Raeef Ibrahim
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
           navigateToedit()
        }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.note.observe(this, Observer { note ->
            if (note != null) {
                tvTitle.text = note.title
                tvLastUpdated.text = getString(R.string.last, note.lastUpdated.toString())
                tvNote.text = note.text
            }
        })
    }
    private fun navigateToedit() {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra(EditActivity.EXTRA_NOTE, mainActivityViewModel.note.value)
        startActivity(intent)
    }
}
