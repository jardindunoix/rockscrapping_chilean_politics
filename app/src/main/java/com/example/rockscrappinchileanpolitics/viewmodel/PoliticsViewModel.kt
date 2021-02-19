package com.example.rockscrappinchileanpolitics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.diputados.DiputadosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos.PartidosPolitcosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.model.managers.senadores.SenadoresActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity
import kotlinx.coroutines.launch

class PoliticsViewModel(application: Application) : AndroidViewModel(application) {
    var diputadosActualesList: MutableLiveData<MutableList<DiputadoActualEntity>> =
        MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
    var senadoresActualesList: MutableLiveData<MutableList<SenadorActualEntity>> =
        MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
    var partidosActualesList: MutableLiveData<MutableList<PartidoPoliticoEntity>> =
        MutableLiveData<MutableList<PartidoPoliticoEntity>>()

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

    init {
        if (partidosActualesList.value.isNullOrEmpty()) {
            getPartidosActualesList()
        }
    }

    private fun getDiputadosActualesList() {
        diputadosActualesList =
            DiputadosActualesWebScrapManager(getApplication()).allDiputadosActuales
        viewModelScope.launch {
            diputadosActualesList
        }
    }

    private fun getSenadoresActualesList() {
        senadoresActualesList =
            SenadoresActualesWebScrapManager(getApplication()).allSenadoresActuales
        viewModelScope.launch {
            senadoresActualesList
        }
    }

    private fun getPartidosActualesList() {
        partidosActualesList =
            PartidosPolitcosActualesWebScrapManager(getApplication()).allPartidosActuales
        viewModelScope.launch {
            partidosActualesList
        }
    }
}
