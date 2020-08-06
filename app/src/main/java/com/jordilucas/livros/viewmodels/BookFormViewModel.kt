package com.jordilucas.livros.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jordilucas.livros.firebase.FbRepository
import com.jordilucas.livros.model.Book

class BookFormViewModel: ViewModel() {

    private val repo = FbRepository()
    var book: Book? = null

    private var showProgress = MutableLiveData<Boolean>().apply { value = false }
    private var saveBook = MutableLiveData<Book>()
    private var savingBookOperation = Transformations.switchMap(saveBook){book ->
        showProgress.value = true
        Transformations.map(repo.saveBook(book)){success ->
            showProgress.value = false
            success
        }
    }

    fun showProgress():LiveData<Boolean> = showProgress
    fun savingOperation(): LiveData<Boolean> = savingBookOperation

    fun saveBook(book: Book){
        saveBook.value = book
    }

}