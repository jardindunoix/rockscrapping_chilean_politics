package com.example.rockscrappinchileanpolitics.b_viewmodel.comunal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.managers.comunal.consejales.ComunasConsejalesActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.c_model.managers.comunal.consejales.ConsejalesActualesDetalleWebScrapManager
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ConsejalActualDetalleEntity
import kotlinx.coroutines.launch

class ComunasConsejalesActualesViewModel(application: Application) : AndroidViewModel(application) {

    var comunasConsejalesActualesList =
        MutableLiveData<MutableList<ComunaConsejalActualEntity>>(mutableListOf())
    var detailConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>(mutableListOf())

    init {
        if (comunasConsejalesActualesList.value.isNullOrEmpty()) {
            getConsejalesActualesList()
        }
    }

    private fun getConsejalesActualesList() {
        comunasConsejalesActualesList = ComunasConsejalesActualesWebScrapManager().allComunasConsejales
        viewModelScope.launch {
            comunasConsejalesActualesList
        }
    }

    fun getConsejalesDetailUsingViewModel(comuna: String){
        detailConsejales = ConsejalesActualesDetalleWebScrapManager(comuna).allConsejales
        viewModelScope.launch {
            detailConsejales
        }
    }
}
