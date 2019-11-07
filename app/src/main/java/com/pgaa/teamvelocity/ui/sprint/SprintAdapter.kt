package com.pgaa.teamvelocity.ui.sprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint

class SprintAdapter internal constructor(context: Context, onClicklistener: OnSprintInteractionListener) : RecyclerView.Adapter<SprintAdapter.SprintViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var sprintList = emptyList<Sprint>()
    private val listener = onClicklistener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SprintViewHolder {
        val itemView = inflater.inflate(R.layout.item_sprint, parent, false)
        return SprintViewHolder(itemView)
    }

    override fun getItemCount() = sprintList.size

    override fun onBindViewHolder(holder: SprintViewHolder, position: Int) {
        val sprint =  sprintList[position]
        holder.sprintName.text =sprint.name
        holder.sprintName.setOnClickListener { listener.onSprintClick(sprint) }
        holder.deleteButton.setOnClickListener { listener.onDeleteClick(sprint) }
    }

    internal fun setSprintList(sprintList: List<Sprint>) {
        this.sprintList = sprintList
        notifyDataSetChanged()
    }

    inner class SprintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sprintName: TextView = itemView.findViewById(R.id.sprint_name)
        val deleteButton: ImageView = itemView.findViewById(R.id.imageView_delete)
    }

}

interface OnSprintInteractionListener {
    // TODO: Update argument type and name
    fun onSprintClick(sprint: Sprint)
    fun onDeleteClick(sprint: Sprint)
}