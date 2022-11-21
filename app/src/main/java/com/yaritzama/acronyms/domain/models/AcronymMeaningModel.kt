package com.yaritzama.acronyms.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AcronymMeaningModel(
    val sf: String
): Parcelable