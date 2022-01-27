package com.althaafridha.smartalarm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.althaafridha.smartalarm.data.Alarm
import com.althaafridha.smartalarm.databinding.ItemRowReminderAlarmBinding

class AlarmAdapter: RecyclerView.Adapter<AlarmAdapter.MyViewHolder>() {

    var listAlarm: List<Alarm> = arrayListOf()

   inner class MyViewHolder(val binding: ItemRowReminderAlarmBinding):
       RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemRowReminderAlarmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
    )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val alarm = listAlarm[position]
       holder.binding.apply {
           itemDateAlarm.text = alarm.date
           itemTimeAlarm.text = alarm.time
           itemNoteAlarm.text = alarm.message
       }
    }

    override fun getItemCount(): Int = listAlarm.size

    fun setData(data: List<Alarm>){
        this.listAlarm = data
    }
}