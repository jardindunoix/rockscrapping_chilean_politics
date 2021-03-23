package com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales.ConsejalesActualesDetalleWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import kotlinx.coroutines.launch

class ConsejalesActualesDetalleViewModel(application: Application, private val comuna: String) :
    AndroidViewModel(application) {

    var consejalesActualesDetalleList =
        MutableLiveData<MutableList<ConsejalActualDetalleEntity>>(mutableListOf())

    init {
        if (consejalesActualesDetalleList.value.isNullOrEmpty()) {
            getConsejalesActualesList()
        }
    }

    private fun getConsejalesActualesList() {
        consejalesActualesDetalleList =
            ConsejalesActualesDetalleWebScrapManager(comuna).allConsejales
        viewModelScope.launch {
            consejalesActualesDetalleList
        }
    }
}
