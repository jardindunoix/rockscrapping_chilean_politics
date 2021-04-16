package com.example.rockscrappinchileanpolitics.a_ui.fragments.partidos_politicos

import android.annotation.SuppressLint
import android.app.Dialog
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
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.b_viewmodel.NetworkViewModel
import com.example.rockscrappinchileanpolitics.b_viewmodel.PartidosPoliticosViewModel
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.databinding.FragmentPartidosPoliticosActualesBinding

class PartidosPoliticosActualesFragment:Fragment() {
	
	private var _binding:FragmentPartidosPoliticosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:PartidosPoliticosViewModel
	private lateinit var modelNetwork:NetworkViewModel
	private lateinit var adapter:PartidosPoliticosAdapter
	
	@SuppressLint("InflateParams")
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?
	):View {
		_binding = FragmentPartidosPoliticosActualesBinding.inflate(layoutInflater)
		adapter = PartidosPoliticosAdapter(mutableListOf(), requireContext())
		initRecyclerView(binding.recyclerViewPartidosPoliticos, requireContext(), adapter)
		val dialogo = Dialog(requireContext(), R.style.Theme_PlayCore_Transparent)
		val view = this.layoutInflater.inflate(R.layout.fullscreen_progress_bar, null)
		dialogo.setContentView(view)
		dialogo.setCancelable(false)
		dialogo.show()
		model = ViewModelProvider(this).get(PartidosPoliticosViewModel::class.java)
		modelNetwork = ViewModelProvider(this).get(NetworkViewModel::class.java)
		modelNetwork.networkStatus.observe(viewLifecycleOwner, { net ->
			if (net == true) {
				model.partidosActualesList.observe(viewLifecycleOwner, {
					adapter.setItemInTheView(it)
					if (it.isNotEmpty()) {
						dialogo.dismiss()
					}
				})
			} else {
				binding.textView.text = getString(R.string.message_sin_conexion)
				dialogo.dismiss()
			}
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