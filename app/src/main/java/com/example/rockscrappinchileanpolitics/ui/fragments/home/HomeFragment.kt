package com.example.rockscrappinchileanpolitics.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentHomeBinding
import com.example.rockscrappinchileanpolitics.viewmodel.header_home.HeaderHomeViewModel
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class HomeFragment:Fragment() {
	
	private var _binding:FragmentHomeBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:HeaderHomeViewModel
	val list = mutableListOf<CarouselItem>()
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?
	):View {
		_binding = FragmentHomeBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(HeaderHomeViewModel::class.java)
		
		model.headerHomeList.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				for ((i, _) in it.withIndex()) {
					list.add(CarouselItem(it[i].webPictureSite))
				}
				binding.carousel.addData(list)
			} else {
				Toast.makeText(
					requireContext(), getString(R.string.text_for_header_without_connection), Toast
						.LENGTH_LONG
				).show()
			}
		})
		
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) = with(binding) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
		buttonIrADipActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_diputadosActualesFragment)
		}
		
		buttonIrASenActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_senadoresActualesFragment)
		}
		
		buttonPartPoliticos.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_partidosActualesFragment)
		}
		buttonIrAConsejActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_consejalesActualesFragment)
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		list.clear()
		_binding = null
	}
}