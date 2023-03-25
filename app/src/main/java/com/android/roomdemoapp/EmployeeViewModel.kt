package com.android.roomdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private var employeeDao: EmployeeDao? = null

    init {
        employeeDao = EmployeeDatabase.getInstance(application).employeeDao()
    }

    fun addEmployee(employeeEntity: EmployeeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            employeeDao?.insert(employeeEntity)
        }
    }

}