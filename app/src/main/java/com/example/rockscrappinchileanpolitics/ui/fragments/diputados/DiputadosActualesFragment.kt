package com.example.rockscrappinchileanpolitics.ui.fragments.diputados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.databinding.FragmentDiputadosActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.diputados.DiputadosActualesAdapter
import com.example.rockscrappinchileanpolitics.viewmodel.PoliticsViewModel

class DiputadosActualesFragment : Fragment() {
	
	private var _binding : FragmentDiputadosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController : NavController
	private lateinit var model : PoliticsViewModel
	private lateinit var adapter : DiputadosActualesAdapter
	override fun onCreateView(inflater : LayoutInflater ,
	                          container : ViewGroup? ,
	                          savedInstanceState : Bundle?) : View {
		_binding = FragmentDiputadosActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(PoliticsViewModel::class.java)
		adapter = DiputadosActualesAdapter(mutableListOf() ,
			requireContext())
		
		initRecyclerView()
		
		model.diputadosActualesList.observe(viewLifecycleOwner ,
			{
				adapter.setItemInTheView(it)
			})
		return binding.root
	}
	
	private fun initRecyclerView() =
		with(binding) {
			recyclerViewDiputadosActuales.hasFixedSize()
			recyclerViewDiputadosActuales.layoutManager =
				LinearLayoutManager(requireContext())
			recyclerViewDiputadosActuales.adapter = adapter
		}
	
	override fun onViewCreated(view : View ,
	                           savedInstanceState : Bundle?) {
		super.onViewCreated(view ,
			savedInstanceState)
		navController = Navigation.findNavController(view)
	}
}