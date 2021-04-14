package com.example.rockscrappinchileanpolitics.a_ui.fragments.gallery

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
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import com.example.rockscrappinchileanpolitics.b_viewmodel.GalleryViewModel
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:GalleryViewModel
	private lateinit var navController:NavController
	private val list = mutableListOf<CarouselItem>()
	private lateinit var dialog:AlertDialog
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(GalleryViewModel::class.java)
		val builder = AlertDialog.Builder(requireContext())
		val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
		val message = dialogView.findViewById<TextView>(R.id.text_view_)
		message.text = getText(R.string.progress_cargando)
		builder.setView(dialogView)
		builder.setCancelable(false)
		dialog = builder.create()
		dialog.show()
		model.galleryList.observe(viewLifecycleOwner, {
			for ((i, _) in it.withIndex()) {
				list.add(CarouselItem(it[i].webPictureSite))
			}
			binding.carousel.addData(list)
		})
		dialog.dismiss()
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