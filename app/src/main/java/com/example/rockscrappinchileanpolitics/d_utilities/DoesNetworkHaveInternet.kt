package com.example.rockscrappinchileanpolitics.d_utilities

import android.util.Log
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

object DoesNetworkHaveInternet {
	
	// Make sure to execute this on a background thread.
	fun execute(socketFactory:SocketFactory):Boolean {
		return try {
			Log.e(TAG, "PINGING -----> especial.")
			val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
			val url = "${StaticUtils.BASE_URL_DIP_ACT}${StaticUtils.END_POINT_DIP_ACT}"
			socket.connect(InetSocketAddress(url, 53), 1500)
			socket.close()
			Log.e(TAG, "PING success.")
			true
		} catch (e:IOException) {
			Log.e(TAG, "No internet connection. $e")
			false
		}
	}
}
