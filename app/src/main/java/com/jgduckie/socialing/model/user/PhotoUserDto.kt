package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName

data class PhotoUserDto(
@SerializedName("_id")
    var _id: String?,
@SerializedName("url")
var url:String?


)