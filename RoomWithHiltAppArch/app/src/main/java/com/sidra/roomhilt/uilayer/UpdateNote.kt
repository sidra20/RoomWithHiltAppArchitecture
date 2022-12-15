package com.sidra.roomhilt.uilayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sidra.roomhilt.R
import com.sidra.roomhilt.databinding.FragmentAllNotesBinding
import com.sidra.roomhilt.databinding.FragmentUpdateNoteBinding
import com.sidra.roomhilt.datalayer.Note
import com.sidra.roomhilt.uilayer.viewmodel.MyViewModel
import com.sidra.roomhilt.uilayer.viewmodel.NotesModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNote : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var viewModel: MyViewModel
    @Inject
    lateinit var factory: NotesModelFactory

    val args:UpdateNoteArgs by navArgs()
    var noteid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        viewModel= ViewModelProvider(this,factory).get(MyViewModel::class.java)

        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_updateNote_to_allNotes)
        }

        noteid=args.id
        binding.titleUpdate.setText(args.title)
        binding.descUpdate.setText(args.desc)

        binding.update.setOnClickListener {
            val t = binding.titleUpdate.text.toString()
            val d = binding.descUpdate.text.toString()
            val i = noteid.toInt()
            if(t.isNotEmpty() and d.isNotEmpty())
            {
                val note = Note(i,t,d)
                viewModel.updateNote(note)
                findNavController().navigate(R.id.action_updateNote_to_allNotes)
                Toast.makeText(context,"Note Updated!", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(context,"Field can't be empty!", Toast.LENGTH_SHORT).show()

            }

        }
        return binding.root
    }

}