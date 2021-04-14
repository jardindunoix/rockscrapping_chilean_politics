package com.example.rockscrappinchileanpolitics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity_:AppCompatActivity(), RecyclerAdapter.RecyclerItemClick, SearchView
.OnQueryTextListener {
	
	private var rvLista:RecyclerView? = null
	private var svSearch:SearchView? = null
	private var adapter:RecyclerAdapter? = null
	private var items:List<ItemList>? = null
	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initViews()
		initValues()
		initListener()
	}
	
	private fun initViews() {
		rvLista = findViewById(R.id.rvLista)
		svSearch = findViewById(R.id.svSearch)
	}
	
	private fun initValues() {
		val manager = LinearLayoutManager(this)
		rvLista !!.layoutManager = manager
		items = getItems()
		adapter = RecyclerAdapter(items, this)
		rvLista !!.adapter = adapter
	}
	
	private fun initListener() {
		svSearch !!.setOnQueryTextListener(this)
	}
	
	private fun getItems():MutableList<ItemList> {
		val itemLists:MutableList<ItemList> = ArrayList<ItemList>()
		itemLists.add(ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.",
			R.drawable.saga_broly))
		itemLists.add(ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.",
			R.drawable.ssj4s))
		itemLists.add(
			ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.",
				R.drawable.ssj_blues))
		itemLists.add(ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.",
			R.drawable.ultrainsitinto))
		itemLists.add(
			ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.",
				R.drawable.super_vegeta))
		itemLists.add(
			ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue))
		itemLists.add(ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.",
			R.drawable.saga_broly))
		itemLists.add(ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.",
			R.drawable.ssj4s))
		itemLists.add(
			ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.",
				R.drawable.ssj_blues))
		itemLists.add(ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.",
			R.drawable.ultrainsitinto))
		itemLists.add(
			ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.",
				R.drawable.super_vegeta))
		itemLists.add(
			ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue))
		return itemLists
	}
	
	fun itemClick(item:ItemList?) {
		val intent = Intent(this, DetailActivity::class.java)
		intent.putExtra("itemDetail", item)
		startActivity(intent)
	}
	
	override fun onQueryTextSubmit(query:String):Boolean {
		return false
	}
	
	override fun onQueryTextChange(newText:String):Boolean {
		adapter !!.filter(newText)
		return false
	}
}


