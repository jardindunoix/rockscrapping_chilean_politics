package com.example.rockscrappinchileanpolitics.c_model.d_managers.header_home

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.GalleryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryWebScrapManager {
	
	var allGalleryPictures = MutableLiveData<MutableList<GalleryEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allGalleryPictures.postValue(getAllBadges())
		}
	}
	
	private fun getAllBadges():MutableList<GalleryEntity> {
		return WebScrapCall.getGallery()
	}
}