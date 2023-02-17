package com.example.lab8mysql_queryinsert

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8mysql_queryinsert.databinding.EmpItemLayoutBinding

class EmployeeAdapter (val employeeList:ArrayList<Employee>?,val context: Context):
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){
    inner class ViewHolder(view: View, val binding: EmpItemLayoutBinding):
        RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmpItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.txtName.text = "Name: ${employeeList!![position].emp_name}"
        binding.txtEmail.text = "Email: ${employeeList!![position].emp_mail}"
        binding.txtGender.text = "Gender: ${employeeList!![position].emp_gender}"
        binding.txtSalary.text = "Salary: ${employeeList!![position].emp_salary}"


    }

    override fun getItemCount(): Int {
        return  employeeList!!.size
    }
}