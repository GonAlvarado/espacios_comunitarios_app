package com.gonnadev.espacioscomunitariosapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.gonnadev.espacioscomunitariosapp.R
import com.gonnadev.espacioscomunitariosapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
        detailViewModel.getDetail(args.id)
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect {
                    when (it) {
                        DetailState.Loading -> loadingState()
                        is DetailState.Error -> errorState()
                        is DetailState.Success -> succesState(it)
                    }
                }
            }
        }
    }

    fun loadingState(){}

    fun errorState(){}

    fun succesState(it: DetailState.Success){
        binding.tvName.text = it.detail.nombre
        binding.tvType.text = it.detail.tipo
        binding.tvAddress.text = it.detail.direccion
        binding.tvDistrict.text = it.detail.barrio
        binding.tvDay.text = it.detail.dia.joinToString(", ") { it.dia }
        binding.tvTime.text = it.detail.horario.joinToString(", ") { it.horario }
        binding.tvAttendance.text = buildString {
            append(it.detail.asistencia.toString())
            append(" Personas")
        }
        val name = buildString {
            append(it.detail.referente.nombre)
            append(" ")
            append(it.detail.referente.apellido)
        }
        binding.tvContact.text = name

        val coordinates = it.detail.coordenadas
        binding.btnRoute.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=$coordinates&mode=d")
            val mapsIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapsIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapsIntent)
        }

        val contact = buildString {
            append("+549")
            append(it.detail.referente.telefono)
        }

        binding.btnContact.setOnClickListener {
            val uriCall = Uri.parse("tel:$contact")
            val uriWsp = Uri.parse("https://api.whatsapp.com/send?phone=$contact")
            val uriSms = Uri.parse("smsto:$contact")

            val intentCall = Intent(Intent.ACTION_DIAL, uriCall)
            val intentWsp = Intent(Intent.ACTION_VIEW, uriWsp)
            val intentSms = Intent(Intent.ACTION_SENDTO, uriSms).apply {
                putExtra("sms_body", "Hola, te contacto desde mi app.")
            }

            val opciones = Intent.createChooser(intentCall, "Comunicate con $name").apply {
                putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentSms, intentWsp))
            }

            startActivity(opciones)
        }

    }

}