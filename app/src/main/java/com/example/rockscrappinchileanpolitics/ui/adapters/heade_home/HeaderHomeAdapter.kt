package com.example.rockscrappinchileanpolitics.ui.adapters.heade_home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemComunasConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.databinding.ItemHeaderHomeBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
import com.squareup.picasso.Picasso

class HeaderHomeAdapter(
	private var list:MutableList<HeaderHomeEntity> = mutableListOf(),
	private val context:Context,
	// private var listenerImgViewShort:ListenerConsejalComunas,
	// private var listenerImgViewLong:ListenerConsejalComunas,
):RecyclerView.Adapter<HeaderHomeAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemHeaderHomeBinding):
		BaseViewHolder<HeaderHomeEntity>(binding.root) {
		
		override fun bind(item:HeaderHomeEntity) = with(binding) {
			Picasso.get().load(item.webPictureSite).into(imageViewHeaderHome)
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding = ItemHeaderHomeBinding.inflate(LayoutInflater.from(context),
			parent,
			false)
		val holder = MyOwnViewHolder(binding)
		// binding.root.setOnClickListener {
		// 	val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
		// 		?: return@setOnClickListener
		//
		// 	this.listenerImgViewShort.viewTouchedShort(position, list[position])
		// }
		// binding.root.setOnLongClickListener {
		// 	val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
		// 		?: return@setOnLongClickListener true
		// 	this.listenerImgViewLong.viewTouchedLong(position, list[position])
		//
		// 	return@setOnLongClickListener true
		// }
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
	
	fun setItemInTheView(comunaConsejalesActuales:MutableList<HeaderHomeEntity>) {
		this.list = comunaConsejalesActuales
		notifyDataSetChanged()
	}
	
}
