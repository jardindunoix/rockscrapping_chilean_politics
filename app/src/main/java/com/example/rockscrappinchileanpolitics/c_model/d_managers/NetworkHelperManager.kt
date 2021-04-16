package com.example.rockscrappinchileanpolitics.c_model.d_managers

import android.content.Context
import android.util.Log
import com.example.rockscrappinchileanpolitics.d_utilities.NetworkHelper

class NetworkHelperManager(context:Context) {
	
	init {
		try {
			getNetworkStatus(context)
		} catch (e:Exception) {
			Log.e("NetWork ERRORRRR ---->", e.message.toString())
		}
	}
	
	companion object {
		
		fun getNetworkStatus(context:Context):Boolean {
			return NetworkHelper.isNetworkConnected(context)
		}
	}
}