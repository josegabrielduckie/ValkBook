package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName

 class UserDataResponse(
     @SerializedName("jwt")
     var jwt: String?,
     @SerializedName("user")
    var user: UserDto?
 )

