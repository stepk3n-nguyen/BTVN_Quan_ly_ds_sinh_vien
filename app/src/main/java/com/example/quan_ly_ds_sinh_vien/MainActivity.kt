package com.example.quan_ly_ds_sinh_vien

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editMSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var gridView: GridView

    private val studentList = mutableListOf<String>()
    private var selectedPosition = -1
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editName)
        editMSSV = findViewById(R.id.editMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        gridView = findViewById(R.id.listViewStudents)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList)
        gridView.adapter = adapter

        btnAdd.setOnClickListener {
            val name = editName.text.toString()
            val mssv = editMSSV.text.toString()
            if (name.isNotBlank() && mssv.isNotBlank()) {
                studentList.add("$name - $mssv")
                adapter.notifyDataSetChanged()
                clearInput()
            }
        }

        btnUpdate.setOnClickListener {
            if (selectedPosition != -1) {
                val name = editName.text.toString()
                val mssv = editMSSV.text.toString()
                if (name.isNotBlank() && mssv.isNotBlank()) {
                    studentList[selectedPosition] = "$name - $mssv"
                    adapter.notifyDataSetChanged()
                    clearInput()
                    selectedPosition = -1
                }
            }
        }

        btnDelete.setOnClickListener {
            if (selectedPosition != -1) {
                studentList.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()
                clearInput()
                selectedPosition = -1
            }
        }

        gridView.setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            val selected = studentList[position]
            val parts = selected.split(" - ")
            if (parts.size == 2) {
                editName.setText(parts[0])
                editMSSV.setText(parts[1])
            }
        }
    }

    private fun clearInput() {
        editName.setText("")
        editMSSV.setText("")
    }
}
