package com.jgduckie.socialing.model.user

import com.google.gson.annotations.SerializedName

data class Jwt(@SerializedName("jwt")var token  :String )