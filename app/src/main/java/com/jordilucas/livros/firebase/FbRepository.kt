package com.jordilucas.livros.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jordilucas.livros.model.Book

class FbRepository {

    private val fbAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val currentUser = fbAuth.currentUser

    fun saveBook(book: Book):LiveData<Boolean>{
        return object : LiveData<Boolean>(){
            override fun onActive() {
                super.onActive()

                if(currentUser == null){
                    throw SecurityException("Usuario invalido")
                }
                val db = firestore
                val collection = db.collection(BOOKS_KEY)
                val saveTask = if(book.id.isBlank()){
                    book.userId = currentUser.uid
                    collection.add(book).continueWith { task ->
                        if(task.isSuccessful){
                            book.id = task.result?.id?:""
                        }
                    }
                }else{
                    collection.document(book.id).set(book, SetOptions.merge())
                }
                saveTask.addOnCompleteListener {
                    value = true
                }.addOnFailureListener {
                    value = false
                }

            }
        }
    }

    companion object{
        const val BOOKS_KEY = "books"
        const val USER_ID_KEY = "userId"
        const val COVER_URL_KEY = "coverUrl"
    }

}