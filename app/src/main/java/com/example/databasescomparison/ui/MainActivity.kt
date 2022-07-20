package com.example.databasescomparison.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasescomparison.R
import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.model.timer.DbTimer
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainModel {

    private val presenter: MainPresenter by inject()

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var sqlohTime: TextView
    private lateinit var roomTime: TextView
    private lateinit var realmTime: TextView
    private lateinit var objectBoxTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) presenter.attachView(this)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        editText = findViewById(R.id.edittext)
        button = findViewById(R.id.button)
        sqlohTime = findViewById(R.id.sqloh_time)
        roomTime = findViewById(R.id.room_time)
        realmTime = findViewById(R.id.realm_time)
        objectBoxTime = findViewById(R.id.object_box_time)

        button.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                presenter.requestByQuery(editText.text.toString())
            } else {
                presenter.requestHeadliners()
            }
        }
    }

    override fun showNewsList(data: List<Article>) {
        runOnUiThread {
            val adapter = NewsAdapter(data)
            recyclerView.adapter = adapter
        }
    }

    override fun updateTimerResults(dbTimer: DbTimer) {
        runOnUiThread {
            sqlohTime.text = dbTimer.sqlohTime.toText()
            roomTime.text = dbTimer.roomTime.toText()
        }
    }

    private fun Long.toText() = "$this ms"
}