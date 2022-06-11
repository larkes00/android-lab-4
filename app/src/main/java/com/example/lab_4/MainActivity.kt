package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_4.databinding.ActivityMainBinding
import com.github.javafaker.Faker
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val books: MutableList<Book> = mutableListOf()
    private val faker: Faker = Faker()
    private lateinit var adapter: BookRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = BookRecycleAdapter(layoutInflater, this, null)
        adapter.listener = object : BookRecycleAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                books.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.submitList(books)
        binding.add.setOnClickListener {
            val imagesArray = resources.obtainTypedArray(R.array.drawer_icons)
            val icon = imagesArray.getResourceId((0..1).random(), -1)
            books.add(
                Book(
                    icon,
                    faker.book().title(),
                    faker.book().genre(),
                    faker.number().randomDouble(2, 50, 500)
                )
            )
            adapter.notifyItemInserted(books.size)
        }
        binding.remove.setOnClickListener {
            adapter.notifyItemRemoved(books.size)
            books.removeLast()
        }
        binding.clean.setOnClickListener {
            adapter.notifyItemRangeRemoved(0, books.size)
            books.clear()
        }
    }

    data class Book(val image: Int, val name: String, val category: String, val price: Double)
}