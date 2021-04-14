package com.example.rockscrappinchileanpolitics.c_model.d_managers.header_home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.GalleryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryManager {
	
	var allGalleryPictures = MutableLiveData<MutableList<GalleryEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				allGalleryPictures.postValue(getAllBadges())
			} catch (e:Exception) {
				Log.e("ERRORRRRR ---->", e.toString())
			}
		}
	}
	
	private fun getAllBadges():MutableList<GalleryEntity> {
		return WebScrapCall.getGallery()
	}
}