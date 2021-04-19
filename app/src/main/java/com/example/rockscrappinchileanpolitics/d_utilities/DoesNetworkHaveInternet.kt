package com.example.rockscrappinchileanpolitics.d_utilities

import android.util.Log
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import javax.net.SocketFactory

object DoesNetworkHaveInternet {
	
	// Make sure to execute this on a background thread.
	fun execute(socketFactory:SocketFactory):Boolean {
		var socket = Socket()
		return try {
			Log.e(TAG, "PINGING -----> especial.")
			socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
			val url = StaticUtils.GALLERY_URL
			socket.connect(InetSocketAddress(url, 53), 1500)
			Log.e(TAG, "PING success.")
			true
		} catch (e:IOException) {
			Log.e(TAG, "No internet connection. $e")
			//no entiendo porque hayq que darle true
			true
		} finally {
			socket.close()
		}
	}
}
