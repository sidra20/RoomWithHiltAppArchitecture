package com.sidra.roomhilt.uilayer

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidra.roomhilt.R
import com.sidra.roomhilt.databinding.FragmentAllNotesBinding
import com.sidra.roomhilt.datalayer.Note
import com.sidra.roomhilt.uilayer.adapter.NotesAdapter
import com.sidra.roomhilt.uilayer.viewmodel.MyViewModel
import com.sidra.roomhilt.uilayer.viewmodel.NotesModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllNotes : Fragment(), NotesAdapter.Listener{
    private lateinit var binding:FragmentAllNotesBinding
    private lateinit var viewModel: MyViewModel
    @Inject
    lateinit var factory: NotesModelFactory

    private lateinit var adapter: NotesAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentAllNotesBinding.inflate(layoutInflater,container,false)
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_allNotes_to_addNote)
        }

        viewModel=ViewModelProvider(this,factory).get(MyViewModel::class.java)
        binding.notesRv.layoutManager=LinearLayoutManager(requireContext())
        adapter= NotesAdapter(requireContext(), this)
        binding.notesRv.adapter=adapter



        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                viewModel.notes.observe(requireActivity(), Observer{
                    adapter.updateList(it)

                    binding.progress.visibility=View.GONE

                })            }
        },1400)




        return binding.root
    }

    override fun itemClick(note: Note) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Update/Delete")
            .setCancelable(true)
            .setMessage("Do you want to update or delete that Note?")
            .setPositiveButton("Update", { dialogInterface, i ->

            })
            .setNegativeButton("Delete", {
                    dialogInterface, i->
                viewModel.deleteNote(note)
                Toast.makeText(context,"Note deleted!", Toast.LENGTH_SHORT).show()
            })
            .setNeutralButton("Close", {dialogInterface, i->
                dialogInterface.cancel()
            }).show()
    }

}