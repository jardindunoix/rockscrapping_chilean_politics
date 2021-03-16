package com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ConsejalesActualesAdapter.MyOwnViewHolder

class ConsejalesActualesAdapter(
	private var list:MutableList<String> = mutableListOf(), private val context:Context
):RecyclerView.Adapter<ConsejalesActualesAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemConsejalesActualesBinding):
		BaseViewHolder<String>(binding.root) {
		
		override fun bind(item:String) = with(binding) {
			textViewNombreConsejalActual.text = item
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding =
			ItemConsejalesActualesBinding.inflate(LayoutInflater.from(context), parent, false)
		
		return MyOwnViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder:BaseViewHolder<*>, position:Int) {
		when (holder) {
			is MyOwnViewHolder -> holder.bind(list[position])
		}
	}
	
	override fun getItemCount():Int {
		return list.size
	}
	
	fun setItemInTheView(comunaConsejalesActuales:MutableList<String>) {
		this.list = comunaConsejalesActuales
		notifyDataSetChanged()
	}
	
}