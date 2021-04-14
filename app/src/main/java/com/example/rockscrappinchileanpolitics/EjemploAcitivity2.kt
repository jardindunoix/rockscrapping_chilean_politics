package com.example.rockscrappinchileanpolitics

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity:AppCompatActivity() {
	
	private var imgItemDetail:ImageView? = null
	private var tvTituloDetail:TextView? = null
	private var tvDescripcionDetail:TextView? = null
	private var itemDetail:ItemList? = null
	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)
		title = javaClass.simpleName
		initViews()
		initValues()
	}
	
	private fun initViews() {
		imgItemDetail = findViewById(R.id.imgItemDetail)
		tvTituloDetail = findViewById(R.id.tvTituloDetail)
		tvDescripcionDetail = findViewById(R.id.tvDescripcionDetail)
	}
	
	private fun initValues() {
		itemDetail = intent.extras !!.getSerializable("itemDetail") as ItemList?
		imgItemDetail !!.setImageResource(itemDetail.getImgResource())
		tvTituloDetail.setText(itemDetail.getTitulo())
		tvDescripcionDetail.setText(itemDetail.getDescripcion())
	}
}
