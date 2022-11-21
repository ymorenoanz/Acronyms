package com.yaritzama.acronyms.data.models

data class LfsResponse(
    val lf: String,
    val freq: Int,
    val since: Int,
    val vars: List<LfsResponse>? = null
)
