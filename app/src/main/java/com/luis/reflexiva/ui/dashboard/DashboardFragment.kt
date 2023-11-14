package com.daniele.tarefas2.ui.tarefa

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.luis.reflexiva.R
import com.luis.reflexiva.Todo
import com.luis.reflexiva.TodoAdapter
import com.luis.reflexiva.databinding.FragmentTarefaBinding

class TarefaFragment : Fragment() {

    private var _binding: FragmentTarefaBinding? = null
    private lateinit var todoAdapter: TodoAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        todoAdapter = TodoAdapter(mutableListOf())

        _binding = FragmentTarefaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvTodoItems = binding.rvTodoItems
        val btnAddTodo = binding.btnAddTodo
        val etTodoTitle = binding.etTodoTitle
        val btnDeleteDoneTodos = binding.btnDeleteDoneTodos

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(requireContext())


        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.length < 3){
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Necessário no mínimo 3 caracteres")
                builder.setPositiveButton("OK, entendi!") { dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton }
                builder.show()
            }
            if (todoTitle.length >= 3) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Finalizar/Excluir!")
            builder.setMessage("Atenção, voce está prestes á finalizar/excluir uma tarefa diaria. Deseja continuar?")
            builder.setPositiveButton("SIM") { dialog, _ -> todoAdapter.deleteDoneTodos() }
            builder.setNegativeButton("NAO") { dialog, _ -> dialog.dismiss() }
            builder.show()
        }

        return root
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.excluir -> {
                val position = todoAdapter.getCurrentPosition();

                todoAdapter.deleteOne(position);
            }
            R.id.cancelar -> {
                Log.d("menu", "cancelado")
            }
        }

        return super.onContextItemSelected(item)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
