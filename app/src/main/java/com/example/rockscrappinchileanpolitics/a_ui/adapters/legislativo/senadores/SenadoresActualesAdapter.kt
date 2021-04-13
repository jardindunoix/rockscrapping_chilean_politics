package com.example.rockscrappinchileanpolitics.a_ui.adapters.legislativo.senadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemSenadoresActualesBinding
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.senadores.SenadorActualEntity
import com.squareup.picasso.Picasso

class SenadoresActualesAdapter(private var list:MutableList<SenadorActualEntity> = mutableListOf(),
	private val context:Context):
	RecyclerView.Adapter<SenadoresActualesAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemSenadoresActualesBinding):
		BaseViewHolder<SenadorActualEntity>(binding.root) {
		
		override fun bind(item:SenadorActualEntity) = with(binding) {
			Picasso.get().load(item.picture).into(imageViewSenadorActual)
			textViewNombreSenadorActual.text = item.nombre
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding =
			ItemSenadoresActualesBinding.inflate(LayoutInflater.from(context), parent, false)
		
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
	
	fun setItemInTheView(diputadosActuales:MutableList<SenadorActualEntity>?) {
		if (diputadosActuales != null) {
			this.list = diputadosActuales
			notifyDataSetChanged()
		}
	}
	
}
