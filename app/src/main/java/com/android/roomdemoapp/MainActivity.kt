package com.android.roomdemoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.roomdemo.EmployeeViewModel
import com.android.roomdemo.EmployeeEntity
import com.android.roomdemoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var employeeViewModel: EmployeeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        binding?.btnAddRecord?.setOnClickListener {
            addRecord()
        }
    }

    private fun addRecord() {
        var name = binding?.etName?.text.toString()
        var email = binding?.etEmail?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty()) {
            lifecycleScope.launch {
                val employeeEntity = EmployeeEntity(name = name, email = email)
                employeeViewModel?.addEmployee(employeeEntity)
                Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_LONG).show()
                clearFields()
            }
        }
    }

    private fun clearFields() {
        binding?.etName?.text?.clear()
        binding?.etEmail?.text?.clear()
    }
}