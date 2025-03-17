package com.gonnadev.espacioscomunitariosapp.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gonnadev.espacioscomunitariosapp.databinding.ItemListBinding
import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemListBinding.bind(view)

    fun render(espacioModel: EspacioModel, onItemSelected: (EspacioModel) -> Unit) {
        binding.tvItemList.text = espacioModel.nombre
        itemView.setOnClickListener { onItemSelected(espacioModel) }
    }
}