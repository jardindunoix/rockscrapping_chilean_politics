package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.d_managers.NetworkHelperManager
import com.example.rockscrappinchileanpolitics.d_utilities.ConnectionLiveData
import kotlinx.coroutines.withContext

class NetworkViewModel(application:Application):AndroidViewModel(application) {
	
	var networkStatus = MutableLiveData<Boolean>()
	
	init {
		getNetwokStatus(application)
	}
	
	private fun getNetwokStatus(context:Context) {
		// val netStat = NetworkHelperManager(context)
		// networkStatus.value = netStat.getNetworkStatusReceiver(context)
	}
}