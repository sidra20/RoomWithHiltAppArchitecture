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
import com.sidra.roomhilt.domainlayer.Event
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
    private val statusmsg = MutableLiveData<Event<String>>()
    val msg:LiveData<Event<String>>
    get() = statusmsg
    var saveupdateBtn = MutableLiveData<String>()
    var isUpdate = false
    private lateinit var noteToUpdate : Note

    init{
        viewModelScope.launch {
            repository.notes
        }
        saveupdateBtn.value="Save"
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
    fun initUpdate(note: Note)
    {
        title.value=note.title
        desc.value=note.detail
        isUpdate=true
        noteToUpdate=note
        saveupdateBtn.value="Update"
    }

    fun saveNote()
    {
        try {
            if(title.value!!.isNotEmpty() && desc.value!!.isNotEmpty()){
                val t = title.value!!.toString()
                val d = desc.value!!.toString()

                val obj = Note(0,t,d)
                insertNote(obj)
                title.value=""
                desc.value=""

                statusmsg.value = Event("Note added successfully!")


//            Toast.makeText(getApplication(), "Note Added!", Toast.LENGTH_SHORT).show()
            }
        }
        catch (e:Exception){
            Log.d("main",""+e.message)
        }

    }




}