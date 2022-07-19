package com.example.databasescomparison.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasescomparison.R
import com.example.databasescomparison.data.remote.WebService
import com.example.databasescomparison.data.remote.model.NewsBody
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.koin.android.ext.android.inject
import java.io.IOException

class MainActivity : AppCompatActivity(), MainModel {

    private val presenter: MainPresenter by inject()

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) presenter.attachView(this)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        editText = findViewById(R.id.edittext)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                presenter.requestByQuery(editText.text.toString())
            }
        }
    }

    override fun showNewsList(data: NewsBody) {
        runOnUiThread {
            val adapter = NewsAdapter(data.articles)
            recyclerView.adapter = adapter
        }
    }
}