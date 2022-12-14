package com.sidra.roomhilt.uilayer.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidra.roomhilt.datalayer.MyRepository
import com.sidra.roomhilt.datalayer.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    var note= Note()
    var notes = repository.notes
    var title = MutableLiveData<String>()
    var desc = MutableLiveData<String>()

    init{
        viewModelScope.launch {
            repository.notes
        }
    }

    fun insertNote(note: Note)= viewModelScope.launch {
        repository.insert(note)
    }

    fun deleteNote(note: Note)= viewModelScope.launch {
        repository.delete(note)
    }

    fun updateNote(note: Note)= viewModelScope.launch {
        repository.update(note)
    }

    fun saveNote()
    {
        try {
            if(title.value!!.isNotEmpty() && desc.value!!.isNotEmpty()){
                val t = title.value!!.toString()
                val d = desc.value!!.toString()

                val obj = Note(0,t,d)
                insertNote(obj)


//            Toast.makeText(getApplication(), "Note Added!", Toast.LENGTH_SHORT).show()
            }
        }
        catch (e:Exception){
            Log.d("main",""+e.message)
        }

    }




}