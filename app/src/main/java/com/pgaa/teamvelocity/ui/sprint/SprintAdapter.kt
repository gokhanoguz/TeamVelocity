package com.pgaa.teamvelocity.ui.sprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint

class SprintAdapter internal constructor(context: Context) : RecyclerView.Adapter<SprintAdapter.SprintViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var sprintList = emptyList<Sprint>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SprintViewHolder {
        val itemView = inflater.inflate(R.layout.item_sprint, parent, false)
        return SprintViewHolder(itemView)
    }

    override fun getItemCount() = sprintList.size

    override fun onBindViewHolder(holder: SprintViewHolder, position: Int) {
        holder.sprintName.text = sprintList[position].name
    }

    internal fun setSprintLust(sprintList: List<Sprint>) {
        this.sprintList = sprintList
        notifyDataSetChanged()
    }

    inner class SprintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sprintName: TextView = itemView.findViewById(R.id.sprint_name)
    }

}