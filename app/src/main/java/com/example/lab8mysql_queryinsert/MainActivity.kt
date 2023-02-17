package com.example.lab8mysql_queryinsert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab8mysql_queryinsert.databinding.ActivityMainBinding
import com.example.lab8mysql_queryinsert.databinding.StdItemLayoutBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var studentList = arrayListOf<Student>()
    var employeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = EmployeeAdapter(this.employeeList,applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.getContext(),
            DividerItemDecoration.VERTICAL)
        )
        //add Student button
        binding.btnAddEmployee.setOnClickListener{
            startActivity(Intent(applicationContext,InsertActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        callEmployeeData()
    }


//    private fun callStudentData(){
//        studentList.clear();
//        val serv : StudentAPI = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:3000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(StudentAPI::class.java)
//
//        serv.retrieveStudent()
//            .enqueue(object : Callback<List<Student>> {
//                override fun onResponse( call: Call<List<Student>>,
//                        response: Response<List<Student>>) {
//                    response.body()?.forEach{
//                        studentList.add(Student(it.std_id,it.std_name,it.std_age))
//                    }
//                    // set Data to RecyclerRecyclerView
//                    binding.recyclerView.adapter = StudentsAdapter(studentList,applicationContext)
//                    binding.txtList.text = "Student List: " + studentList.size.toString()
//                }
//
//                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
//                    Toast.makeText(applicationContext,"Error onFailure"+ t.message,Toast.LENGTH_LONG).show()
//                }
//            })
//    }
private fun callEmployeeData(){
    employeeList.clear();
    val serv : EmployeeAPI = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EmployeeAPI::class.java)

    serv.retrieveEmployee()
        .enqueue(object : Callback<List<Employee>> {
            override fun onResponse( call: Call<List<Employee>>,
                                     response: Response<List<Employee>>) {
                response.body()?.forEach{
                    employeeList.add(Employee(it.emp_name,it.emp_mail,it.emp_gender,it.emp_salary))
                }
                // set Data to RecyclerRecyclerView
                binding.recyclerView.adapter = EmployeeAdapter(employeeList,applicationContext)
//                binding.txtList.text = "Employee List: " + employeeList.size.toString()
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure"+ t.message,Toast.LENGTH_LONG).show()
            }
        })
}


}