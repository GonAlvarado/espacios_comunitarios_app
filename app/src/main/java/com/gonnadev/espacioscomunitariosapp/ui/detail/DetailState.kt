package com.gonnadev.espacioscomunitariosapp.ui.detail

import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel

sealed class DetailState {
    data object Loading : DetailState()
    data class Error(val error: String) : DetailState()
    data class Success(val detail: EspacioModel) : DetailState()
}