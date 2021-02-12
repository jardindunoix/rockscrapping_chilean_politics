package com.example.rockscrappinchileanpolitics.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.diputados.DiputadosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.model.managers.senadores.SenasoresActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity
import kotlinx.coroutines.launch

class PoliticsViewModel(application: Application) : AndroidViewModel(application) {

    var diputadosActualesList: MutableLiveData<MutableList<DiputadoActualEntity>> =
        MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())

    var senadoresActualesList: MutableLiveData<MutableList<SenadorActualEntity>> =
        MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())

    init {
        if (diputadosActualesList.value.isNullOrEmpty()) {
            getDiputadosActualesList()
        }
    }

    init {
        if (senadoresActualesList.value.isNullOrEmpty()) {
            getSenadoresActualesList()
        }
    }

    private fun getDiputadosActualesList() {
        diputadosActualesList =
            DiputadosActualesWebScrapManager(getApplication()).allDiputadosActuales
        viewModelScope.launch {
            diputadosActualesList
            Log.d("DIPUTADOS ---->", "${diputadosActualesList.value}")
        }
    }

    private fun getSenadoresActualesList() {
        senadoresActualesList =
            SenasoresActualesWebScrapManager(getApplication()).allSenadoresActuales
        viewModelScope.launch {
            senadoresActualesList
            Log.d("SENADORES ---->", "${senadoresActualesList.value}")
        }
    }
}
