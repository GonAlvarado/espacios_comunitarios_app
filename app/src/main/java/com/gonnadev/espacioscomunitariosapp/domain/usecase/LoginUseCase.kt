package com.gonnadev.espacioscomunitariosapp.domain.usecase

import com.gonnadev.espacioscomunitariosapp.domain.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}