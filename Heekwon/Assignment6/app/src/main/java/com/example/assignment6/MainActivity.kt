package com.example.assignment6

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    val itemList = arrayListOf<ListItem>()
    val listAdapter = ListAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = listAdapter

        binding.btInputButton.setOnClickListener{
            itemList.add(ListItem(binding.etInput.text.toString()))
            listAdapter.notifyDataSetChanged()
        }

        listAdapter.setOnItemClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("삭제!")
                .setMessage("삭제하시겠습니까")
                .setPositiveButton("네") { _, _ ->
                    itemList.removeAt(it)
                    listAdapter.notifyDataSetChanged()
                }
                .setNegativeButton("아니요") { _, _ ->}
                .show()
        }


        listAdapter.setOnItemLongClickListener {
            val dlg = CustomDialog(this)
            dlg.setOnClickedListener(object : CustomDialog.ButtonClickListener{
                override fun onClicked(myName: String) {
                    itemList[it] = ListItem(myName)
                    listAdapter.notifyDataSetChanged()
                }
            })
            dlg.show()
        }
    }
}
