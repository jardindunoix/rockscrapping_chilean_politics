package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.b_entities.GalleryEntity
import com.example.rockscrappinchileanpolitics.c_model.d_managers.header_home.GalleryManager
import kotlinx.coroutines.launch

class GalleryViewModel(application:Application):AndroidViewModel(application) {
	
	var galleryList = MutableLiveData<MutableList<GalleryEntity>>(mutableListOf())
	
	init {
			getHomeHeaderList()
	}
	
	private fun getHomeHeaderList() {
		galleryList = GalleryManager().allGalleryPictures
		viewModelScope.launch {
			galleryList
		}
	}
}