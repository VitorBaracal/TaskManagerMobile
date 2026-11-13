package com.example.taskmanagermobile.data.api

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("cep") val cep: String? = null,
    @SerializedName("logradouro") val street: String? = null,
    @SerializedName("bairro") val district: String? = null,
    @SerializedName("localidade") val city: String? = null,
    @SerializedName("uf") val state: String? = null,
    @SerializedName("erro") val error: Boolean? = null
)
