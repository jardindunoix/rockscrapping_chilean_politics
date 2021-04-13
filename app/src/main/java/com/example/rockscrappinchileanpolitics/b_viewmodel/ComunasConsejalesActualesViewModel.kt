package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales.ComunasConsejalesActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales.ConsejalesActualesDetalleWebScrapManager
import kotlinx.coroutines.launch

class ComunasConsejalesActualesViewModel(application: Application) : AndroidViewModel(application) {

    var comunasConsejalesActualesList =
        MutableLiveData<MutableList<ComunaConsejalActualEntity>>(mutableListOf())
    var detailConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>(mutableListOf())

    init {
            getConsejalesActualesList()
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
