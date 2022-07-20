package com.example.databasescomparison.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasescomparison.R
import com.example.databasescomparison.data.model.remotenews.Article
import org.koin.android.ext.android.inject

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

    override fun showNewsList(data: List<Article>) {
        runOnUiThread {
            val adapter = NewsAdapter(data)
            recyclerView.adapter = adapter
        }
    }
}