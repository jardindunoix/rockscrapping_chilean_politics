package com.example.rockscrappinchileanpolitics.ui.fragments.comunal.consejales

import android.app.Dialog
import android.os.Bundle
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
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.DialogConsejalesDetailBinding
import com.example.rockscrappinchileanpolitics.databinding.FragmentComunasConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ComunasConsejalesActualesAdapter
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ConsejalesActualesDetalleAdapter
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.utilities.services.interfaces_listeners.ListenerConsejalComunas
import com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal.ComunasConsejalesActualesViewModel

class ComunasConsejalesActualesFragment : Fragment(), ListenerConsejalComunas {

    private var _binding: FragmentComunasConsejalesActualesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: ComunasConsejalesActualesViewModel
    private lateinit var adapter: ComunasConsejalesActualesAdapter
    private var bindingDialog: DialogConsejalesDetailBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentComunasConsejalesActualesBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(ComunasConsejalesActualesViewModel::class.java)
        adapter = ComunasConsejalesActualesAdapter(mutableListOf(), requireContext(), this, this)
        binding.recyclerViewConsejalesActuales.initRecyclerView(binding.recyclerViewConsejalesActuales,
            requireContext(), adapter)
        model.comunasConsejalesActualesList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setItemInTheView(it)
            } else {
                binding.textView.text = getString(R.string.text_for_header_without_connection)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun viewTouchedShort(position: Int, comunaObjeto: ComunaConsejalActualEntity) {
        Toast.makeText(requireContext(), "Consejales de \n\r${comunaObjeto.nombre}",
            Toast.LENGTH_LONG).show()
        displayDetailDialog(comunaObjeto.nombre)
    }

    override fun viewTouchedLong(position: Int, comunaObjeto: ComunaConsejalActualEntity) {
        Toast.makeText(requireContext(), comunaObjeto.paginaWeb, Toast.LENGTH_SHORT).show()
    }

    private fun displayDetailDialog(comuna: String) {
        bindingDialog = DialogConsejalesDetailBinding.inflate(layoutInflater)
        val dialog = Dialog(bindingDialog!!.root.context)
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON)
        dialog.setCancelable(true)
        dialog.setContentView(bindingDialog!!.root)
        val adapter = ConsejalesActualesDetalleAdapter(mutableListOf(), requireContext())
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.hasFixedSize()
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.layoutManager =
            LinearLayoutManager(requireContext())
        bindingDialog!!.recyclerViewConsejalesActualesDetalle.adapter = adapter
        val oldValue1 = " "
        val oldValue2 = "Ñ"
        val newValue1 = "-"
        val newValue2 = "N"
        val comunaM = comuna.trim().replace(oldValue1, newValue1).replace(oldValue2, newValue2)
        model.getConsejalesDetailUsingViewModel(comunaM)
        bindingDialog!!.textView.text = comuna
        model.detailConsejales.observe(this, {
            adapter.setItemInTheView(it)
        })

        bindingDialog!!.btnGoBackDetail.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}