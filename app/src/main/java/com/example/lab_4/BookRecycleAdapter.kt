package com.example.lab_4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BookRecycleAdapter(
    private val inflater: LayoutInflater,
    private val context: Context,
    var listener: OnItemClickListener?,
) :
    ListAdapter<MainActivity.Book, BookRecycleAdapter.ViewHolder>(BookDiffCallback()) {


    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        private val name = view.findViewById<TextView>(R.id.name)
        private val category = view.findViewById<TextView>(R.id.category)
        private val price = view.findViewById<TextView>(R.id.price)
        private val picture = view.findViewById<ImageView>(R.id.picture)
        private val information = view.findViewById<Button>(R.id.information)

        fun bind(book: MainActivity.Book) {
            name.text = book.name
            category.text = book.category
            price.text = book.price.toString()
            picture.setImageResource(book.image)
            information.setOnClickListener {
                val intent = Intent(context, BookInformationActivity::class.java)
                intent.putExtra("name", book.name)
                intent.putExtra("category", book.category)
                startActivity(context, intent, null)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)

    }

    class BookDiffCallback : DiffUtil.ItemCallback<MainActivity.Book>() {

        override fun areItemsTheSame(
            oldItem: MainActivity.Book,
            newItem: MainActivity.Book
        ): Boolean = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: MainActivity.Book,
            newItem: MainActivity.Book
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

}