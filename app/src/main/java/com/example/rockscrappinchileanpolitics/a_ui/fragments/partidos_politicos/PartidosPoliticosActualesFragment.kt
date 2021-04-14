package com.example.rockscrappinchileanpolitics.a_ui.fragments.partidos_politicos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentPartidosPoliticosActualesBinding
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.b_viewmodel.PartidosPoliticosViewModel

class PartidosPoliticosActualesFragment:Fragment() {
	
	private var _binding:FragmentPartidosPoliticosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:PartidosPoliticosViewModel
	private lateinit var adapter:PartidosPoliticosAdapter
	private lateinit var dialog:AlertDialog
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentPartidosPoliticosActualesBinding.inflate(layoutInflater)
		adapter = PartidosPoliticosAdapter(mutableListOf(), requireContext())
		initRecyclerView(binding.recyclerViewPartidosPoliticos, requireContext(), adapter)
		// val builder = AlertDialog.Builder(requireContext())
		// val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
		// val message = dialogView.findViewById<TextView>(R.id.text_view_)
		// message.text = getText(R.string.progress_cargando)
		// builder.setView(dialogView)
		// builder.setCancelable(false)
		// dialog = builder.create()
		model = ViewModelProvider(this).get(PartidosPoliticosViewModel::class.java)
		model.partidosActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
			// dialog.dismiss()
		})
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		// dialog.show()
		YoYo.with(Techniques.Tada).duration(StaticUtils.YOYO_DURATION).playOn(binding.textView)
		navController = Navigation.findNavController(view)
	}
	
	override fun onPause() {
		super.onPause()
		dismissProgressBar()
	}
	
	private fun dismissProgressBar() {
		// if(dialog != null && dialog.isShowing())
		// 	dialog.dismiss()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}