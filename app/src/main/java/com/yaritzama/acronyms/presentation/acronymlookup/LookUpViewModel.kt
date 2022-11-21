package com.yaritzama.acronyms.presentation.acronymlookup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.yaritzama.acronyms.R
import com.yaritzama.acronyms.utils.ResponseStatus
import com.yaritzama.acronyms.data.mappers.toDomain
import com.yaritzama.acronyms.domain.models.AcronymList
import com.yaritzama.acronyms.domain.repository.RepositoryAcronym
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LookUpViewModel @Inject constructor(
    private val repositoryAcronym: RepositoryAcronym) : ViewModel() {

    private val _data = LiveEvent<ResponseStatus<AcronymList>>()
            val data: LiveData<ResponseStatus<AcronymList>>
            get() = _data

    private val _errorEmpty = LiveEvent<Int?>()
    val errorEmpty: LiveData<Int?>
    get() = _errorEmpty

    private fun fetchService(sf: String){
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(ResponseStatus.Loading())
            try{
                val meaningList = repositoryAcronym.getAcronym(sf)
                if(!meaningList.isNullOrEmpty()){
                    var model: AcronymList? = null
                    meaningList.forEach { meanings ->
                        model = meanings.toDomain(sf)
                    }
                    _data.postValue(ResponseStatus.Success(model))
                }else{
                    _data.postValue(ResponseStatus.Error(ERROR_NOT_FOUND, null))
                }

            }catch (e: Exception){
                _data.postValue(ResponseStatus.Error(ERROR_GENERIC, null))
            }
        }
    }

    fun onLookUpClick(text: String){
        if(text.isBlank().not()){
            fetchService(text.trim())
            _errorEmpty.postValue(null)
        }else{
            _errorEmpty.postValue(R.string.error_empty)
        }
    }

    companion object{
        private const val ERROR_NOT_FOUND = "Acronym meaning not found"
        private const val ERROR_GENERIC = "An error occurred while loading data"
    }

}