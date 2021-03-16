package com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemConsejalesActualesDetalleBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.squareup.picasso.Picasso

class ConsejalesActualesDetalleAdapter(
	private var list:MutableList<ConsejalActualDetalleEntity> = mutableListOf(),
	private val context:Context,
):RecyclerView.Adapter<ConsejalesActualesDetalleAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemConsejalesActualesDetalleBinding):
		BaseViewHolder<ConsejalActualDetalleEntity>(binding.root) {
		
		override fun bind(item:ConsejalActualDetalleEntity) = with(binding) {
			textViewNombreConsejalActualDetalle.text = item.nombre
			Picasso.get().load(item.picture).into(imageViewConsejalActualDetalle)
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding =
			ItemConsejalesActualesDetalleBinding.inflate(LayoutInflater.from(context), parent, false)
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
	
	fun setItemInTheView(consejalesActualesDetalle:MutableList<ConsejalActualDetalleEntity>) {
		this.list = consejalesActualesDetalle
		notifyDataSetChanged()
	}
	
}