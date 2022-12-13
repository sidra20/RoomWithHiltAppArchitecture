package com.sidra.roomhilt.uilayer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sidra.roomhilt.datalayer.MyRepository
import javax.inject.Inject

class NotesModelFactory @Inject constructor(private val repository: MyRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(repository)as T
    }
}