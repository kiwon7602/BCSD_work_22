package com.login.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

public class AFragment : Fragment() {
    var count: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActivity = activity as MainActivity
        val btnCount = view.findViewById<Button>(R.id.count_button)
        val btnAlert = view.findViewById<Button>(R.id.alert_button)
        val btnRandom = view.findViewById<Button>(R.id.random_button)
        val tvCount = view.findViewById<TextView>(R.id.count_tv)
        btnCount.setOnClickListener(View.OnClickListener {
            count++
            tvCount.text = count.toString()
        })
        btnAlert.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Dialog")
                .setMessage("카운트를 초기화 하시겠습니까?")
                .setNeutralButton("중립",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(requireContext(), "다음에 다시 눌러줭~", Toast.LENGTH_LONG).show()
                    })
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        count = 0
                        tvCount.text = count.toString()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            builder.show()
        })
        btnRandom.setOnClickListener(View.OnClickListener{
            mActivity.changeFragment(2, count)
        })
    }
}