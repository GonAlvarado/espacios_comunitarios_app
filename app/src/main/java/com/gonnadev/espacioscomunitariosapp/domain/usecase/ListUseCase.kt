package com.gonnadev.espacioscomunitariosapp.domain.usecase

import com.gonnadev.espacioscomunitariosapp.domain.Repository
import javax.inject.Inject

class ListUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getList()
}