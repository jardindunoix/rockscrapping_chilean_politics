package com.example.rockscrappinchileanpolitics.a_ui.fragments.legislativo.diputados

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentDiputadosActualesBinding
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.b_viewmodel.DiputadosActualesViewModel

class DiputadosActualesFragment:Fragment() {
	
	private var _binding:FragmentDiputadosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:DiputadosActualesViewModel
	private lateinit var adapter:DiputadosActualesAdapter
	private lateinit var dialog:androidx.appcompat.app.AlertDialog
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentDiputadosActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(DiputadosActualesViewModel::class.java)
		adapter = DiputadosActualesAdapter(mutableListOf(), requireContext())
		initRecyclerView(binding.recyclerViewDiputadosActuales, requireContext(), adapter)
		val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
		val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
		val message = dialogView.findViewById<TextView>(R.id.text_view_)
		message.text = getText(R.string.progress_cargando)
		builder.setView(dialogView)
		builder.setCancelable(false)
		dialog = builder.create()
		dialog.show()
		model.diputadosActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
			dialog.dismiss()
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