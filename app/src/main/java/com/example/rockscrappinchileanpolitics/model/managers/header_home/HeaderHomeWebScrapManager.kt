package com.example.rockscrappinchileanpolitics.model.managers.header_home

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
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