package com.althaafridha.smartalarm.adapter

import androidx.recyclerview.widget.DiffUtil
import com.althaafridha.smartalarm.data.Alarm

class AlarmDiffutil(private val oldList: List<Alarm>, private val newList: List<Alarm>) :
    DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.id == newData.id
                && oldData.date == newData.date
                && oldData.time == newData.time
                && oldData.message == newData.message
    }

}