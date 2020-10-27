package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName

data class SingUserDto(
    @SerializedName("username") var name: String?,
    @SerializedName("email") var email: String,
    @SerializedName("password") var pass: String?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("apellidos") var apellidos: String?,
)