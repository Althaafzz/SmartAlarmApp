package com.althaafridha.smartalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.althaafridha.smartalarm.data.Alarm
import com.althaafridha.smartalarm.data.local.AlarmDB
import com.althaafridha.smartalarm.databinding.ActivityOneTimeAlarmBinding
import com.althaafridha.smartalarm.fragment.DateDialogFragment
import com.althaafridha.smartalarm.fragment.TimeDialogFragment
import com.althaafridha.smartalarm.helper.timeFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class OneTimeAlarmActivity : AppCompatActivity(),
    DateDialogFragment.DialogDateSetListener, TimeDialogFragment.TimeDialogListener {

    private var _binding : ActivityOneTimeAlarmBinding? = null
    private val binding get() = _binding as ActivityOneTimeAlarmBinding

    private val db by lazy { AlarmDB(this) }
    private var alarmService: AlarmReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityOneTimeAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmService = AlarmReceiver()
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSetDateOneTime.setOnClickListener{
                val datePickerFragment = DateDialogFragment()
                datePickerFragment.show(supportFragmentManager, "DatePickerDialog")
            }
            btnSetTimeOneTime.setOnClickListener {
                val timePickerFragment = TimeDialogFragment()
                timePickerFragment.show(supportFragmentManager, "TimePickerDialog")
            }
            btnAdd.setOnClickListener {
                val date = tvOnceDate.text.toString()
                val time = tvOnceTime.text.toString()
                val message = edtNoteOneTime.text.toString()

                if(date == "Date" && time == "Time"){
                    Toast.makeText(applicationContext,
                        getString(R.string.txt_toast_msg_set_alarm),
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    alarmService?.setOneTimeAlarm(applicationContext, 0, date, time, message)
                    CoroutineScope(Dispatchers.IO).launch {
                        db.alarmDao().addAlarm(
                            Alarm(
                                0,
                                date,
                                time,
                                message,
                                AlarmReceiver.TYPE_ONE_TIME
                            )
                        )
                        Log.i("AddAlarm", "alarm set on $date $time with message $message")
                        finish()
                    }
                }
            }
            btnCancel.setOnClickListener{
                finish()
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        // untuk mengubah tanggal kalender sekarang menjadi tanggal yang telah dipilih di DatePicker
        calendar.set(year, month, dayOfMonth)
        val dateFormated = SimpleDateFormat("dd-MM-yyy", Locale.getDefault())
        binding.tvOnceDate.text = dateFormated.format(calendar.time)
    }

    override fun onTimeListener(tag: String?, hour: Int, minute: Int) {
        binding.tvOnceTime.text = timeFormatter(hour, minute)
    }
}
