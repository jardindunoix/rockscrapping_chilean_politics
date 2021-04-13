package com.example.rockscrappinchileanpolitics.utilities.services.extension_functions

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExtensionFunctions {
	companion object {
		
		fun Context.toast(message:String) {
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
		}
		
		fun initRecyclerView(
			recycler:RecyclerView,
			context:Context,
			adapter_:RecyclerView.Adapter<*>
		) {
			recycler.hasFixedSize()
			recycler.layoutManager = LinearLayoutManager(context)
			recycler.adapter = adapter_
		}
	}
}