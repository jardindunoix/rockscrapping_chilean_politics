package com.example.rockscrappinchileanpolitics.c_model.d_managers

import android.content.Context
import android.util.Log
import com.example.rockscrappinchileanpolitics.d_utilities.ConnectionLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NetworkHelperManager(context:Context) {
	
	private var networkReceiver by Delegates.notNull<Boolean>()
	
	init {
		try {
			CoroutineScope(Dispatchers.IO).launch {
				networkReceiver = getNetworkStatusReceiver(context)
			}
		} catch (e:Exception) {
			Log.e("NetWork ERRORRRR ---->", e.message.toString())
		}
	}
	
	fun getNetworkStatusReceiver(context:Context):Boolean {
		val netActive = ConnectionLiveData(context).value
		
		return when (netActive !!) {
			true -> true
			else -> false
		}
		
	}
}
