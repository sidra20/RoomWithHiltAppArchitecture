package com.sidra.roomhilt.uilayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sidra.roomhilt.R
import com.sidra.roomhilt.databinding.FragmentAddNoteBinding
import com.sidra.roomhilt.uilayer.viewmodel.MyViewModel
import com.sidra.roomhilt.uilayer.viewmodel.NotesModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNote : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    lateinit var viewModel: MyViewModel

    @Inject
    lateinit var factory: NotesModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        viewModel=ViewModelProvider(this, factory).get(MyViewModel::class.java)
        binding.viewmodl=viewModel
        binding.lifecycleOwner=this

        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_addNote_to_allNotes)
        }



        return binding.root
    }

}