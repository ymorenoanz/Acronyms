package com.yaritzama.acronyms.data.repository

import com.yaritzama.acronyms.data.api.AcronymAPI
import com.yaritzama.acronyms.data.models.AcronymMeaningResponse
import com.yaritzama.acronyms.domain.models.AcronymList
import com.yaritzama.acronyms.domain.repository.RepositoryAcronym

class RepositoryAcronymImpl(
    private val api: AcronymAPI
) : RepositoryAcronym{
    override suspend fun getAcronym(sf: String): List<AcronymMeaningResponse>? {
        return api.getAcronymMeaning(sf)
    }
}