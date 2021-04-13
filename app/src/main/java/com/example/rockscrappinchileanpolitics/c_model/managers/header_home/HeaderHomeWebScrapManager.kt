package com.example.rockscrappinchileanpolitics.c_model.managers.header_home

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.entities.header_home.HeaderHomeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeaderHomeWebScrapManager {
	
	var allHeaderBadges = MutableLiveData<MutableList<HeaderHomeEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allHeaderBadges.postValue(getAllBadges())
		}
	}
	
	private fun getAllBadges():MutableList<HeaderHomeEntity> {
		return RepositorioWebScrapCall.getGallery()
	}
}