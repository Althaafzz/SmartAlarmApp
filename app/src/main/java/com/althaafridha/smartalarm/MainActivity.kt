package com.althaafridha.smartalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.althaafridha.smartalarm.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initTimeToday()
//        initDateToday()
        initView()
    }

    private fun initView() {
        binding.apply {
            cvOneTimeAlarm.setOnClickListener{
                startActivity(Intent(applicationContext, OneTimeAlarmActivity::class.java))
            }
            cvRepeatAlarm.setOnClickListener {
                startActivity(Intent(this@MainActivity, RepeatingAlarmActivity::class.java))
            }
        }
    }

    /* TIDAK DIGUNAKAN KARENA MEMAKAI TEXTCLOCK */

//    private fun initDateToday() {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("EEEE, dd MMM yyy", Locale.getDefault())
//        val formatedDate = dateFormat.format(calendar.time)
//
//        binding.tvDateToday.text = formatedDate
//    }
//
//    private fun initTimeToday() {
//        // buat dapetin segala hal yang berhubungan dengan waktu di android
//        val calendar = Calendar.getInstance()
//
//        // menentukan format jam yang akan digunakan, contoh 16.22 atau 04.22 atau 16.22.50
//        // HH = 24jam
//        // "hh:mm aa" format am pm
//        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//        val formatedTime = timeFormat.format(calendar.time)
//
//        binding.tvTimeToday.text = formatedTime
//    }
}