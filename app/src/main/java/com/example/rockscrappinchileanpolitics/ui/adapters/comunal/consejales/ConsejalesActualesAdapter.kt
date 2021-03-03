package com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity

class ConsejalesActualesAdapter(
	private var list:MutableList<ConsejalActualEntity> = mutableListOf(), private val context:Context
):RecyclerView.Adapter<ConsejalesActualesAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemConsejalesActualesBinding):
		BaseViewHolder<ConsejalActualEntity>(binding.root) {
		
		override fun bind(item:ConsejalActualEntity) = with(binding) {
			textViewNombreConsejalActual.text = item.nombre
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding =
			ItemConsejalesActualesBinding.inflate(LayoutInflater.from(context), parent, false)
		val holder = MyOwnViewHolder(binding)
		
		return holder
	}
	
	override fun onBindViewHolder(holder:BaseViewHolder<*>, position:Int) {
		when (holder) {
			is MyOwnViewHolder -> holder.bind(list[position])
		}
	}
	
	override fun getItemCount():Int {
		return list.size
	}
	
	fun setItemInTheView(consejalesActuales:MutableList<ConsejalActualEntity>?) {
		if (consejalesActuales != null) {
			this.list = consejalesActuales
			notifyDataSetChanged()
		}
	}
	
}