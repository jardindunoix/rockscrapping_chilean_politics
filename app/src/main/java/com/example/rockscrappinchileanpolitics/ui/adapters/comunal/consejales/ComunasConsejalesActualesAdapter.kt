package com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.databinding.ItemComunasConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.interfaces_listeners.ListenerConsejalComunas

class ComunasConsejalesActualesAdapter(
    private var list: MutableList<ComunaConsejalActualEntity> = mutableListOf(),
    private val context: Context,
    private var listenerImgViewShort: ListenerConsejalComunas,
    private var listenerImgViewLong: ListenerConsejalComunas,
) : RecyclerView.Adapter<ComunasConsejalesActualesAdapter.BaseViewHolder<*>>() {

    abstract class BaseViewHolder<T>(item: View) : RecyclerView.ViewHolder(item) {
        abstract fun bind(item: T)
    }

    inner class MyOwnViewHolder(val binding: ItemComunasConsejalesActualesBinding) :
        BaseViewHolder<ComunaConsejalActualEntity>(binding.root) {

        override fun bind(item: ComunaConsejalActualEntity) = with(binding) {
            textViewComunaConsejalActual.text = item.nombre
            textViewRegionConsejalActual.text = item.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val binding = ItemComunasConsejalesActualesBinding.inflate(LayoutInflater.from(context),
            parent,
            false)
        val holder = MyOwnViewHolder(binding)

        binding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                ?: return@setOnClickListener

            this.listenerImgViewShort.viewTouchedShort(position, list[position])
        }

        binding.root.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                ?: return@setOnLongClickListener true
            this.listenerImgViewLong.viewTouchedLong(position, list[position])

            return@setOnLongClickListener true
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MyOwnViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItemInTheView(comunaConsejalesActuales: MutableList<ComunaConsejalActualEntity>) {
        this.list = comunaConsejalesActuales
        notifyDataSetChanged()
    }

}