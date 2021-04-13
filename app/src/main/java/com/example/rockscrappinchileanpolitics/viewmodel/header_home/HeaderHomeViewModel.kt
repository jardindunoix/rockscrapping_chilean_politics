package com.example.rockscrappinchileanpolitics.viewmodel.header_home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.header_home.HeaderHomeWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
import kotlinx.coroutines.launch

class HeaderHomeViewModel(application:Application):AndroidViewModel(application) {
	
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