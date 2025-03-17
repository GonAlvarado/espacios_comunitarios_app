package com.gonnadev.espacioscomunitariosapp.ui.list

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gonnadev.espacioscomunitariosapp.databinding.FragmentListBinding
import com.gonnadev.espacioscomunitariosapp.ui.list.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels<ListViewModel>()
    private lateinit var listAdapter: ListAdapter

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listViewModel.getList()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        listAdapter = ListAdapter(onItemSelected = {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailActivity(it.id)
            )
        })
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listViewModel.state.collect {
                    when (it) {
                        ListState.Loading -> loadingState()
                        is ListState.Error -> errorState()
                        is ListState.Success -> succesState(it)
                    }
                }
            }
        }
    }

    fun loadingState() {}

    fun errorState() {}

    fun succesState(it: ListState.Success) {
        val list = it.list

        val daysList = listOf(
            "Todos los espacios",
            "Lunes",
            "Martes",
            "Miércoles",
            "Jueves",
            "Viernes",
            "Sábado",
            "Domingo"
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, daysList)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        val spinner = binding.spList
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDay = parent.getItemAtPosition(position) as String

                if (selectedDay == "Todos los espacios") {
                    listAdapter.updateList(list)
                } else {
                    val filteredList = list.filter { espacio ->
                        espacio.dia.any { it.dia == selectedDay }
                    }
                    listAdapter.updateList(filteredList)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}