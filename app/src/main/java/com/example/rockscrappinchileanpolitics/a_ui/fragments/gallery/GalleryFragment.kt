package com.example.rockscrappinchileanpolitics.a_ui.fragments.gallery

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.b_viewmodel.GalleryViewModel
import com.example.rockscrappinchileanpolitics.d_utilities.ConnectionLiveData
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:GalleryViewModel
	private lateinit var navController:NavController
	private val list = mutableListOf<CarouselItem>()
	private lateinit var connectionLiveData:ConnectionLiveData
	
	@SuppressLint("InflateParams")
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(GalleryViewModel::class.java)
		val dialogo = Dialog(requireContext(), R.style.Theme_PlayCore_Transparent)
		val view = this.layoutInflater.inflate(R.layout.progress_bar, null)
		dialogo.setContentView(view)
		dialogo.setCancelable(true)
		dialogo.setCanceledOnTouchOutside(true)
		dialogo.show()
		binding.textView.text = getString(R.string.message_sin_conexion)
		binding.carousel.visibility = View.INVISIBLE
		connectionLiveData = ConnectionLiveData(requireContext())
		model.galleryList.observe(viewLifecycleOwner, { galleryList ->
			connectionLiveData.observe(viewLifecycleOwner, { isNetAvailable ->
				when (isNetAvailable) {
					true -> {
						dialogo.dismiss()
						Toast.makeText(requireContext(), getString(R.string.progress_cargando),
							Toast.LENGTH_SHORT).show()
						for ((i, _) in galleryList.withIndex()) {
							list.add(CarouselItem(galleryList[i].webPictureSite))
						}
						binding.textView.text = getString(R.string.text_gallery)
						binding.carousel.visibility = View.VISIBLE
						binding.carousel.addData(list)
					}
					
					false -> {
						Toast.makeText(requireContext(), getString(R.string.message_sin_conexion),
							Toast.LENGTH_SHORT).show()
						binding.textView.text = getString(R.string.message_sin_conexion)
						binding.carousel.visibility = View.INVISIBLE
						dialogo.dismiss()
						list.clear()
					}
				}
			})
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