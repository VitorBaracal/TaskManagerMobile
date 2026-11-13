package com.example.taskmanagermobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.data.api.Address
import com.example.taskmanagermobile.data.api.ViaCepApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class CepViewModel : ViewModel() {
    var cep by mutableStateOf("")
        private set
    var address by mutableStateOf<Address?>(null)
        private set
    var loading by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    fun changeCep(value: String) {
        if (loading || value.length > 9) return
        cep = value
        address = null
        message = null
    }

    fun search() {
        if (loading) return
        address = null
        message = null

        if (!Regex("[0-9]{5}-?[0-9]{3}").matches(cep)) {
            message = "Informe um CEP válido."
            return
        }

        loading = true
        viewModelScope.launch {
            try {
                val response = ViaCepApi.service.find(cep.replace("-", ""))
                when {
                    response.error == true -> message = "CEP não encontrado."
                    response.cep.isNullOrBlank() -> message = "Resposta inválida."
                    else -> address = response
                }
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                message = "Não foi possível consultar o CEP."
            } finally {
                loading = false
            }
        }
    }
}
