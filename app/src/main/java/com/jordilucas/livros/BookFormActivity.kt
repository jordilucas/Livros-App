package com.jordilucas.livros

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jordilucas.livros.databinding.ActivityBookBinding
import com.jordilucas.livros.databinding.ActivityBookFormBinding
import com.jordilucas.livros.model.Book
import com.jordilucas.livros.model.MediaType
import com.jordilucas.livros.model.Publisher
import org.parceler.Parcels

class BookFormActivity : AppCompatActivity() {

    private val binding:ActivityBookFormBinding by lazy {
        DataBindingUtil.setContentView<ActivityBookFormBinding>(this, R.layout.activity_book_form)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.content.book = if (savedInstanceState == null){
            Parcels.unwrap<Book>(intent.getParcelableExtra(EXTRA_BOOK))?:Book()
        }else{
            Parcels.unwrap<Book>(savedInstanceState.getParcelable(EXTRA_BOOK))
        }
        binding.content.publishers = listOf(
            Publisher("1", "Novatec"),
            Publisher("2", "Outro")
        )
        binding.content.view = this
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_BOOK, Parcels.wrap(binding.content.book))
    }

    fun onMediaTypeChanged(buttonView:CompoundButton, isChecked:Boolean){
        if(isChecked){
            if(buttonView === binding.content.rbMediaEbook){
                binding.content.book?.mediaType = MediaType.EBOOK
            }else if(buttonView === binding.content.rbMediaPaper){
                binding.content.book?.mediaType = MediaType.PAPER
            }
        }
    }

    fun clickSaveBook(view:View){
        val book = binding.content.book
        if(book != null){
            val s = "${book.title}\n"+
                    "${book.author}\n"+
                    "${book.publisher?.name}\n"+
                    "${book.pages}\n"+
                    "${book.year}\n"+
                    "${book.available}\n"+
                    "${book.rating}\n"+
                    "${book.mediaType}\n"
            Toast.makeText(this, s, Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        private const val EXTRA_BOOK = "book"

        fun start(context: Context, book:Book){
            context.startActivity(Intent(context, BookFormActivity::class.java).apply{
                putExtra(EXTRA_BOOK, Parcels.wrap(book))
            })
        }

    }

}