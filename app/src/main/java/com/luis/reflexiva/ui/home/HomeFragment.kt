package com.daniele.tarefas2.ui.sobre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.luis.reflexiva.databinding.FragmentSobreBinding


class SobreFragment : Fragment() {

    private var _binding: FragmentSobreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSobreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titulo: TextView = binding.titulo
        val texto: TextView = binding.texto
        val cardViewPrincipal: CardView = binding.cardViewPrincipal


        cardViewPrincipal.visibility = View.VISIBLE
        titulo.visibility = View.VISIBLE
        texto.visibility = View.VISIBLE

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
