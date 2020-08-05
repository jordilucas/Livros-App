package com.jordilucas.livros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.jordilucas.livros.adapter.BookAdapter
import com.jordilucas.livros.model.Book
import com.jordilucas.livros.model.MediaType
import com.jordilucas.livros.model.Publisher
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : BaseActivity() {
    override fun init() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.menu.book_list_menu){
            FirebaseAuth.getInstance().signOut()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        val books = listOf(Book().apply {
            id = "1"
            title = "Dominando Android com Kotlin"
            author = "Nelson Glauber"
            coverUrl = "https://s3.novatec.com.br/capas-ampliadas/capa-ampliada-9788575224632.jpg"
            pages = 954
            year = 2018
            publisher = Publisher("1", "Novatec")
            available = true
            mediaType = MediaType.PAPER
            rating = 5.0f
        }
        )
        rvBooks.layoutManager = LinearLayoutManager(this)
        rvBooks.adapter = BookAdapter(books){book ->
            BookDetailsActivity.start(this, book)
        }
    }
}