package com.example.data.entity

import com.example.domain.data.SyntaxErrorDetail
import com.google.gson.annotations.SerializedName

data class PhoneSyntaxErrorDetailEntity(
    @SerializedName("loc")
    val loc: List<String>? = null,

    @SerializedName("msg")
    val msg: String? = null,

    @SerializedName("type")
    val type: String? = null,
) {
    fun toSyntaxErrorDetail(): SyntaxErrorDetail {
        return SyntaxErrorDetail(
            loc = loc ?: listOf(),
            msg = msg ?: "Empty message",
            type = type ?: "Unknown type"
        )
    }
}