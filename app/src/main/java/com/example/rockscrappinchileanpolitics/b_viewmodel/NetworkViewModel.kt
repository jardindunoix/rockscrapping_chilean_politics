package com.example.rockscrappinchileanpolitics.b_viewmodel

// import android.app.Application
// import android.content.Context
// import androidx.lifecycle.AndroidViewModel
// import androidx.lifecycle.MutableLiveData
// import androidx.lifecycle.viewModelScope
// import com.example.rockscrappinchileanpolitics.c_model.d_managers.NetworkHelperManager
// import kotlinx.coroutines.launch
//
// class NetworkViewModel(application:Application):AndroidViewModel(application) {
//
// 	// var networkStatus by Delegates.notNull<Boolean>()
// 	var networkStatus:MutableLiveData<Boolean>? = null
//
// 	init {
// 		if (networkStatus == null) {
// 			getNetwokStatus(application)
// 		}
// 	}
//
// 	private fun getNetwokStatus(context:Context) {
// 		val netHelp = NetworkHelperManager(context)
// 		networkStatus !!.postValue(netHelp.getNetworkStatusReceiver(context))
// 		viewModelScope.launch {
// 			networkStatus
// 		}
// 	}
// }