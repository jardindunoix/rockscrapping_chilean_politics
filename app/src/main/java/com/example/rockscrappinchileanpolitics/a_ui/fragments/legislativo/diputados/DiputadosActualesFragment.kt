package com.example.rockscrappinchileanpolitics.a_ui.fragments.legislativo.diputados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.b_viewmodel.DiputadosActualesViewModel
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.databinding.FragmentDiputadosActualesBinding

class DiputadosActualesFragment:Fragment() {
	
	private var _binding:FragmentDiputadosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:DiputadosActualesViewModel
	private lateinit var adapter:DiputadosActualesAdapter
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentDiputadosActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(DiputadosActualesViewModel::class.java)
		adapter = DiputadosActualesAdapter(mutableListOf(), requireContext())
		initRecyclerView(binding.recyclerViewDiputadosActuales, requireContext(), adapter)
		model.diputadosActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
		})
		
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		YoYo.with(Techniques.Tada).duration(StaticUtils.YOYO_DURATION).playOn(binding.textView)
		navController = Navigation.findNavController(view)
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}