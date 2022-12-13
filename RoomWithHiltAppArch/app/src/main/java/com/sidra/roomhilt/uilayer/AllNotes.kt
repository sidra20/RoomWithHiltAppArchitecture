package com.sidra.roomhilt.uilayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sidra.roomhilt.R
import com.sidra.roomhilt.databinding.FragmentAllNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNotes : Fragment() {
    private lateinit var binding:FragmentAllNotesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentAllNotesBinding.inflate(layoutInflater,container,false)
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_allNotes_to_addNote)
        }
        return binding.root
    }

}