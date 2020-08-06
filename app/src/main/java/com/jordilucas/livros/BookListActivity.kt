package com.jordilucas.livros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.jordilucas.livros.adapter.BookAdapter
import com.jordilucas.livros.model.Book
import com.jordilucas.livros.model.MediaType
import com.jordilucas.livros.model.Publisher
import com.jordilucas.livros.viewmodels.BookListViewModel
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : BaseActivity() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProviders.of(this).get(BookListViewModel::class.java)
    }

    override fun init() {
        try{
            viewModel.getBooks().observe(this, Observer { books ->
                updateList(books)
            })
        }catch (e:Exception){
            Toast.makeText(this, R.string.message_error_load_books, Toast.LENGTH_SHORT).show()
        }
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
       fabAdd.setOnClickListener { startActivity(Intent(this, BookFormActivity::class.java)) }
    }

    private fun updateList(books:List<Book>){
        rvBooks.layoutManager = LinearLayoutManager(this)
        rvBooks.adapter = BookAdapter(books){book ->
            BookDetailsActivity.start(this, book)
        }
    }
}