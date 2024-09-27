package com.example.assignment12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment12.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel = MainViewModel()

    private var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            when(result.resultCode){
                1 -> {
                    val data : Intent? = result.data
                    val post = PostData(
                        data?.getStringExtra("Title").toString(),
                        data?.getStringExtra("Content").toString(),
                        data?.getStringExtra("User").toString(),
                        Date(System.currentTimeMillis())
                    )
                    viewModel.add(post)
                }

                2 -> {
                    val data: Intent? = result.data
                    val post = PostData(
                        data?.getStringExtra("Title").toString(),
                        data?.getStringExtra("Content").toString(),
                        data?.getStringExtra("User").toString(),
                        Date(System.currentTimeMillis())
                    )
                    viewModel.modify(post, position)
                }

            }
        }

        val view = binding.root
        val adapter = RecyclerViewAdapter(emptyList(), object : RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                this@MainActivity.position = position
                val intent = Intent(this@MainActivity, ModifyPostActivity::class.java)
                resultLauncher.launch(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                viewModel.delete(position)
            }
        })
        setContentView(view)

        binding.rvPost.layoutManager = LinearLayoutManager(this)
        binding.rvPost.adapter = adapter

        adapter.setItemClickListener(object : RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                this@MainActivity.position = position
                val intent = Intent(this@MainActivity, ModifyPostActivity::class.java)
                resultLauncher.launch(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                viewModel.delete(position)
            }
        })

        viewModel.postLiveData.observe(this){
            (binding.rvPost.adapter as RecyclerViewAdapter).setData(it)
        }

        binding.add.setOnClickListener{
            val intent = Intent(this, AddPostActivity::class.java)
            resultLauncher.launch(intent)
        }


    }
}