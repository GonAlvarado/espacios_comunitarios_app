package com.gonnadev.espacioscomunitariosapp.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonnadev.espacioscomunitariosapp.domain.usecase.ListUseCase
import com.gonnadev.espacioscomunitariosapp.ui.list.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val listUseCase: ListUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<ListState>(ListState.Loading)
    val state: StateFlow<ListState> = _state

    fun getList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { listUseCase() }
            if (result.isNotEmpty()) {
                _state.value = ListState.Success(result)
            } else {
                _state.value = ListState.Error("Ha ocurrido un error")
            }
        }
    }

}