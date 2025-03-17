package com.gonnadev.espacioscomunitariosapp.domain.usecase

import com.gonnadev.espacioscomunitariosapp.domain.Repository
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getDetail(id)
}