package com.example.rockscrappinchileanpolitics.b_viewmodel.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.managers.header_home.HeaderHomeWebScrapManager
import com.example.rockscrappinchileanpolitics.c_model.entities.header_home.HeaderHomeEntity
import kotlinx.coroutines.launch

class GalleryViewModel(application:Application):AndroidViewModel(application) {
	
	var headerHomeList = MutableLiveData<MutableList<HeaderHomeEntity>>(mutableListOf())
	
	init {
		if (headerHomeList.value.isNullOrEmpty()) {
			getHomeHeaderList()
		}
	}
	
	private fun getHomeHeaderList() {
		headerHomeList = HeaderHomeWebScrapManager().allHeaderBadges
		viewModelScope.launch {
			headerHomeList
		}
	}
}