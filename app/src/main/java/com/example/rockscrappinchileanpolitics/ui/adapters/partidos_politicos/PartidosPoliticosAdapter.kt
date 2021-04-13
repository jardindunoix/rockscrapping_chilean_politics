package com.example.rockscrappinchileanpolitics.ui.adapters.partidos_politicos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.databinding.ItemPartidosPoliticosBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity

class PartidosPoliticosAdapter(
	private var list:MutableList<PartidoPoliticoEntity> = mutableListOf(),
	private val context:Context):
	RecyclerView.Adapter<PartidosPoliticosAdapter.BaseViewHolder<*>>() {
	
	abstract class BaseViewHolder<T>(item:View):RecyclerView.ViewHolder(item) {
		
		abstract fun bind(item:T)
	}
	
	inner class MyOwnViewHolder(val binding:ItemPartidosPoliticosBinding):
		BaseViewHolder<PartidoPoliticoEntity>(binding.root) {
		
		override fun bind(item:PartidoPoliticoEntity) = with(binding) {
			textViewNombrePartidoActual.text = item.nombre
		}
	}
	
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BaseViewHolder<*> {
		val binding =
			ItemPartidosPoliticosBinding.inflate(LayoutInflater.from(context), parent,
				false)
		
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
	
	fun setItemInTheView(partidosActuales:MutableList<PartidoPoliticoEntity>?) {
		if (partidosActuales != null) {
			this.list = partidosActuales
			notifyDataSetChanged()
		}
	}
	
}
