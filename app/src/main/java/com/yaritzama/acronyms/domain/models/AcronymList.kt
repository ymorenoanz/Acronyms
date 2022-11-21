package com.yaritzama.acronyms.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AcronymList(
    val sf: String,
    val lfs: List<AcronymMeaningModel>
): Parcelable