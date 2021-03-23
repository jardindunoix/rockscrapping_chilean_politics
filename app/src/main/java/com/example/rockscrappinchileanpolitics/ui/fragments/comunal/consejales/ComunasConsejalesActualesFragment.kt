package com.example.rockscrappinchileanpolitics.ui.fragments.comunal.consejales

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.DialogDetailBinding
import com.example.rockscrappinchileanpolitics.databinding.FragmentComunasConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ComunasConsejalesActualesAdapter
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ConsejalesActualesDetalleAdapter
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.interfaces_listeners.ListenerConsejalComunas
import com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal.ComunasConsejalesActualesViewModel


class ComunasConsejalesActualesFragment : Fragment(), ListenerConsejalComunas {

    private var _binding: FragmentComunasConsejalesActualesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: ComunasConsejalesActualesViewModel
    private lateinit var adapter: ComunasConsejalesActualesAdapter
    private var bindingDialog: DialogDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComunasConsejalesActualesBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(ComunasConsejalesActualesViewModel::class.java)
        adapter = ComunasConsejalesActualesAdapter(mutableListOf(), requireContext(), this, this)

        initRecyclerView()

        model.comunasConsejalesActualesList.observe(viewLifecycleOwner, {
            adapter.setItemInTheView(it)
        })
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        recyclerViewConsejalesActuales.hasFixedSize()
        recyclerViewConsejalesActuales.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewConsejalesActuales.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun viewTouchedShort(
        position: Int, comunaObjeto: ComunaConsejalActualEntity,
    ) {
        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_LONG).show()
    }

    override fun viewTouchedLong(position: Int, comunaObjeto: ComunaConsejalActualEntity) {
        Toast.makeText(requireContext(), comunaObjeto.nombre, Toast.LENGTH_LONG).show()
        displayDetailDialog(comunaObjeto.nombre)
    }

    private fun displayDetailDialog(comuna: String) {
        bindingDialog = DialogDetailBinding.inflate(layoutInflater)
        val dialog = Dialog(bindingDialog!!.root.context)
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON)
        dialog.setCancelable(true)
        dialog.setContentView(bindingDialog!!.root)
        val width = resources.getDimensionPixelSize(R.dimen.popup_width)
        val height = resources.getDimensionPixelSize(R.dimen.popup_height)
        dialog.window!!.setLayout(width, height)
        val adapter =
            ConsejalesActualesDetalleAdapter(mutableListOf(), requireContext())
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.hasFixedSize()
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.layoutManager =
            LinearLayoutManager(requireContext())
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.adapter = adapter
        model.getConsejalesDetailUsingViewModel(comuna)
        bindingDialog!!.textView.text = comuna
        Log.d("Message ---->", comuna)
        model.detailConsejales.observe(this, {
            adapter.setItemInTheView(it)
        })

        bindingDialog!!.btnGoBackDetail.setOnClickListener {
            dialog.dismiss()
        }

        bindingDialog!!.btnGoToSendMail.setOnClickListener {
            YoYo.with(Techniques.Shake)
                .duration(400)
                .playOn(bindingDialog!!.textView)
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}