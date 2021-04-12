package com.example.rockscrappinchileanpolitics.ui.fragments.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import com.example.rockscrappinchileanpolitics.databinding.FragmentHomeBinding
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.viewmodel.header_home.HeaderHomeViewModel
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:HeaderHomeViewModel
	private lateinit var navController:NavController
	val list = mutableListOf<CarouselItem>()
	
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?
	):View? {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(HeaderHomeViewModel::class.java)
		
		model.headerHomeList.observe(viewLifecycleOwner, {
			for ((i, _) in it.withIndex()) {
				list.add(CarouselItem(it[i].webPictureSite))
			}
			binding.carousel.addData(list)
		})
		
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		list.clear()
		_binding = null
	}
}