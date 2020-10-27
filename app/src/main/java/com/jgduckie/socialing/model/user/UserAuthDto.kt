package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName


data class UserAuthDto (
    @SerializedName("identifier")
    val name: String?,
    @SerializedName("password")
    val password: String?
    )