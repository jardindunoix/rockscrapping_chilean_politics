package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.header_home

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
import java.io.IOException

class HeaderHomeWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<HeaderHomeEntity>>() {
	
	private var comunasList:ArrayList<HeaderHomeEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<HeaderHomeEntity> {
		try {
			comunasList =
				RepositorioWebScrapCallss.getHeaderBadges() as ArrayList<HeaderHomeEntity>
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return comunasList
	}
}
}