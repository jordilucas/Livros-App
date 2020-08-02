package com.jordilucas.livros

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jordilucas.livros.databinding.ActivityBookBinding
import com.jordilucas.livros.model.Book
import org.parceler.Parcels

class BookDetailsActivity : AppCompatActivity() {

    private val binding: ActivityBookBinding by lazy {
        DataBindingUtil.setContentView<ActivityBookBinding>(
            this, R.layout.activity_book_details
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val book = Parcels.unwrap<Book>(intent.getParcelableExtra(EXTRA_BOOK))
        if (book != null) {
            binding.book = book
        }
    }

    companion object {
        private const val EXTRA_BOOK = "book"
        fun start(context: Context, book: Book) {
            context.startActivity(
                Intent(context, BookDetailsActivity::class.java).apply {
                    putExtra(EXTRA_BOOK, Parcels.wrap(book))
                }
            )
        }
    }

}