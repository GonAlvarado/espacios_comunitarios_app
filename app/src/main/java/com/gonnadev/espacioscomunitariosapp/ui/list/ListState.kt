package com.gonnadev.espacioscomunitariosapp.ui.list

import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel

sealed class ListState {
    data object Loading : ListState()
    data class Error(val error: String) : ListState()
    data class Success(val list: List<EspacioModel>) : ListState()
}