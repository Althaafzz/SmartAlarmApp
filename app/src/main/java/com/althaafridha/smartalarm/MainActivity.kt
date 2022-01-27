package com.althaafridha.smartalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaafridha.smartalarm.adapter.AlarmAdapter
import com.althaafridha.smartalarm.data.Alarm
import com.althaafridha.smartalarm.data.local.AlarmDB
import com.althaafridha.smartalarm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var alarmAdapter: AlarmAdapter? = null

    private val db by lazy { AlarmDB(this) }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            val alarm = db.alarmDao().getAlarm()
            alarmAdapter?.setData(alarm)
            Log.i("GetAlarm", "SetupRecyclerView: with this data $alarm")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        binding.apply {
            rvReminderAlarm.apply {
                alarmAdapter = AlarmAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = alarmAdapter
            }
        }
    }

    private fun initView() {
        binding.apply {
            cvOneTimeAlarm.setOnClickListener {
                startActivity(Intent(applicationContext, OneTimeAlarmActivity::class.java))
            }
            cvRepeatAlarm.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        RepeatingAlarmActivity::class.java
                    )
                )
            }
        }
    }
}