package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("_id")
    var _id: String?,
    @SerializedName("username")
    var username: String?,//USERNAME NICK
    @SerializedName("password")
    var pass: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("nombre")
    var nombre: String?,
    @SerializedName("apellidos")
    var apellidos: String?,
    @SerializedName("photo_profile")
    var avatar: PhotoUserDto?,
    @SerializedName("name")
    var name: String?


)