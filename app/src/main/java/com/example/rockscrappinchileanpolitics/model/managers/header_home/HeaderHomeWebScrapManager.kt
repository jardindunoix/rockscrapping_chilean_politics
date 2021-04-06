package com.example.rockscrappinchileanpolitics.model.managers.header_home

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.header_home.HeaderHomeWebScrap
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity

class HeaderHomeWebScrapManager {
	var allHeaderBadges = MutableLiveData<MutableList<HeaderHomeEntity>>(mutableListOf())
	
	init {
		allHeaderBadges.value = getAllBadges()
	}
	
	private fun getAllBadges():MutableList<HeaderHomeEntity>? {
		val list:MutableList<HeaderHomeEntity>
		val loader:AsyncTask<Void, Void, ArrayList<HeaderHomeEntity>>?
		loader = HeaderHomeWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get()
		return list
	}
}