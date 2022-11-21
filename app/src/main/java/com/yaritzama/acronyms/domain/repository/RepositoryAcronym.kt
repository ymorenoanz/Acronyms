package com.yaritzama.acronyms.domain.repository

import com.yaritzama.acronyms.data.models.AcronymMeaningResponse

interface RepositoryAcronym {
    suspend fun getAcronym(sf: String) : List<AcronymMeaningResponse>?
}