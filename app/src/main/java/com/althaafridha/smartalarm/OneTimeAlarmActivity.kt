package com.althaafridha.smartalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.althaafridha.smartalarm.databinding.ActivityOneTimeAlarmBinding
import com.althaafridha.smartalarm.helper.timeFormatter
import java.text.SimpleDateFormat
import java.util.*

class OneTimeAlarmActivity : AppCompatActivity(),
    DateDialogFragment.DialogDateSetListener, TimeDialogFragment.TimeDialogListener {

    private var _binding : ActivityOneTimeAlarmBinding? = null
    private val binding get() = _binding as ActivityOneTimeAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityOneTimeAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
