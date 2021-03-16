package com.example.rockscrappinchileanpolitics.ui.fragments.comunal.consejales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.databinding.FragmentConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ConsejalesActualesAdapter
import com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal.ConsejalesActualesViewModel

class ConsejalesActualesFragment:Fragment() {
	
	private var _binding:FragmentConsejalesActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:ConsejalesActualesViewModel
	private lateinit var adapter:ConsejalesActualesAdapter
	
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View? {
		_binding = FragmentConsejalesActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(ConsejalesActualesViewModel::class.java)
		adapter = ConsejalesActualesAdapter(mutableListOf(), requireContext())
		
		initRecyclerView()
		
		model.consejalesActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
		})
		return binding.root
		
	}
	
	private fun initRecyclerView() = with(binding) {
		recyclerViewConsejalesActuales.hasFixedSize()
		recyclerViewConsejalesActuales.layoutManager = LinearLayoutManager(requireContext())
		recyclerViewConsejalesActuales.adapter = adapter
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}
}