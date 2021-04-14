package com.example.rockscrappinchileanpolitics

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.RecyclerAdapter.RecyclerHolder
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class RecyclerAdapter(private val items:MutableList<ItemList>, itemClick:RecyclerItemClick):
	RecyclerView.Adapter<RecyclerHolder>() {
	
	private val originalItems:MutableList<ItemList>
	
	// private val itemClick:RecyclerItemClick = itemClick
	override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):RecyclerHolder {
		val view:View =
			LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
		return RecyclerHolder(view)
	}
	
	override fun onBindViewHolder(holder:RecyclerAdapter.RecyclerHolder, position:Int) {
		val item:ItemList = items[position]
		holder.imgItem.setImageResource(item.imgResource)
		holder.tvTitulo.text = item.titulo
		holder.tvDescripcion.text = item.descripcion
		holder.itemView.setOnClickListener {
			itemClick(item)
		}
		/*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
	}
	
	override fun getItemCount():Int {
		return items.size
	}
	
	fun filter(strSearch:String) {
		if (strSearch.isEmpty()) {
			items.clear()
			items.addAll(originalItems)
		} else {
			items.clear()
			val collect = originalItems.stream().filter { i:ItemList ->
				i.titulo.toLowerCase(Locale.ROOT).contains(strSearch)
			}.collect(Collectors.toList()) // as List<ItemList>
			items.addAll(collect)
		}
		notifyDataSetChanged()
	}
	
	inner class RecyclerHolder(itemView_1:View):RecyclerView.ViewHolder(itemView_1) {
		
		val imgItem:ImageView = itemView.findViewById(R.id.imgItem)
		val tvTitulo:TextView = itemView.findViewById(R.id.tvTitulo)
		val tvDescripcion:TextView = itemView.findViewById(R.id.tvDescripcion)
		
	}
	
	interface RecyclerItemClick {
		
		fun itemClick(item:ItemList?)
	}
	
	init {
		originalItems = ArrayList()
		originalItems.addAll(items)
	}
}