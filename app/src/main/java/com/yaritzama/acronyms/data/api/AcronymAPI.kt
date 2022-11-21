package com.yaritzama.acronyms.data.api

import com.yaritzama.acronyms.data.models.AcronymMeaningResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymAPI {
    @GET("dictionary.py")
    suspend fun getAcronymMeaning(
        @Query("sf") sf: String): List<AcronymMeaningResponse>?
}