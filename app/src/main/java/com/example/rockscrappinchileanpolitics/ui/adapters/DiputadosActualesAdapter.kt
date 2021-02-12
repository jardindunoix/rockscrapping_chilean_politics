package com.example.rockscrappinchileanpolitics.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemDiputadosActualesBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity

class DiputadosActualesAdapter(private var list : MutableList<DiputadoActualEntity> = mutableListOf() ,
                               private val context : Context) : RecyclerView.Adapter<DiputadosActualesAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item : View) : RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item : T)
	}
	
	inner class MyOwnViewHolder(val binding : ItemDiputadosActualesBinding) : BaseViewHolder<DiputadoActualEntity>(binding.root) {
		
		override fun bind(item : DiputadoActualEntity) =
			with(binding) {
				textViewNombreDiputadoActual.text = item.nombre
				textViewWebpage.text = item.paginaWeb
			}
	}
	
	override fun onCreateViewHolder(parent : ViewGroup ,
	                                viewType : Int) : BaseViewHolder<*> {
		val binding = ItemDiputadosActualesBinding.inflate(LayoutInflater.from(context) ,
			parent ,
			false)
		val holder = MyOwnViewHolder(binding)
		
		return holder
	}
	
	override fun onBindViewHolder(holder : BaseViewHolder<*> ,
	                              position : Int) {
		when (holder) {
			is MyOwnViewHolder -> holder.bind(list[position])
		}
	}
	
	override fun getItemCount() : Int {
		return list.size
	}
	
	fun setItemInTheView(diputadosActuales : MutableList<DiputadoActualEntity>?) {
		if (diputadosActuales != null) {
			this.list = diputadosActuales
			notifyDataSetChanged()
		}
	}
	
}