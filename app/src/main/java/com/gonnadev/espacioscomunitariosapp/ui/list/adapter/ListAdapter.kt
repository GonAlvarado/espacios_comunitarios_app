package com.gonnadev.espacioscomunitariosapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gonnadev.espacioscomunitariosapp.R
import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel

class ListAdapter(private var espaciosList: List<EspacioModel> = emptyList(), private val onItemSelected: (EspacioModel) -> Unit) :
    RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount() = espaciosList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.render(espaciosList[position], onItemSelected)
    }

    fun updateList(list: List<EspacioModel>) {
        espaciosList = list
        notifyDataSetChanged()
    }
}