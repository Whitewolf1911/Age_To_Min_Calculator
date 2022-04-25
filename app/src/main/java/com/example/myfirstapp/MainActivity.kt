package com.example.myfirstapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvDatePicked)
        fun clickDatePicker(){
            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)
            val dpd =  DatePickerDialog(this , DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Year was $selectedYear month was $selectedMonth  ", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate.time / 60000  // it gives it in ms so you divide it to make in min
                val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val tvMinutesPassed: TextView = findViewById(R.id.tvMinutesPassed)
                tvMinutesPassed.text = differenceInMinutes.toString()
            },
                    year, month,
                    day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
           dpd.show()


        }

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }


    }
}

