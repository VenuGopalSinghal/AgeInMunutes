package com.example.ageinmunutes

import android.app.DatePickerDialog
import android.icu.text.SymbolTable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

//for makeing the app full screen
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            clickDatePicker(view)

        }

    }

    fun clickDatePicker(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                /*
                Toast.makeText(

                    this,
                    "date is $selectedyear,$selectedmonth,$selecteddayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
 */
                val selectedDate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear"
                selecteddatetext.text = selectedDate


                val simpleDateFormat = SimpleDateFormat("ss/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)

                val selecteddatetime = theDate!!.time / 60000

                val currentDate =
                    simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                val currntdateinminutes = currentDate!!.time / 60000

                timeinminutestext.text = (currntdateinminutes - selecteddatetime).toString()


//                val totalminutes =(
//                    ((year - selectedyear) * 365) + (day - selecteddayOfMonth) + (month - selectedmonth * 30) * 1440)
//                timeinminutestext.text= totalminutes.toString()


            }
            , year
            , month
            , day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}
