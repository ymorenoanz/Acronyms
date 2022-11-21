package com.yaritzama.acronyms.data.mappers

import com.yaritzama.acronyms.data.models.AcronymMeaningResponse
import com.yaritzama.acronyms.data.models.LfsResponse
import com.yaritzama.acronyms.domain.models.AcronymList
import com.yaritzama.acronyms.domain.models.AcronymMeaningModel

fun AcronymMeaningResponse.toDomain(sf: String): AcronymList{
    val lfList = mutableListOf<AcronymMeaningModel>()
    lfs.forEach { lfItem ->
        lfList.add(lfItem.toDomain(lfItem.lf))
    }
    return AcronymList(sf, lfList)
}

fun LfsResponse.toDomain(lf: String): AcronymMeaningModel = AcronymMeaningModel(lf)