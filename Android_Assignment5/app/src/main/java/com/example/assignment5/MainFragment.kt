package com.example.assignment5

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
        private val fragment = SubFragment()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_main, container, false)
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val mActivity = activity as MainActivity
            var num = 0
            val print_num = view.findViewById<TextView>(R.id.print_number)
            val plus_number = view.findViewById<Button>(R.id.count_btn)
            val dialog_box = view.findViewById<Button>(R.id.dialog_btn)
            val random_btn = view.findViewById<Button>(R.id.random_btn)

            arguments?.let { //액티비티에서 값을 받으면 넣음
                num = it.getInt("key", 0)
                print_num.text = num.toString()
            }

            plus_number.setOnClickListener{
                num++
                print_num.text = num.toString()
            }

            dialog_box.setOnClickListener()
            {
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Dialog")
                    .setMessage("현재 숫자")
                    .setPositiveButton("확인"){ _, _ ->
                        num = 0
                        print_num.text = num.toString()
                    }
                    .setNegativeButton("취소", null)
                    .setNeutralButton("토스트메시지"){ _, _ ->
                        Toast.makeText(requireContext(), "현재 입력된 숫자는 " + num.toString() + "입니다." ,Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }

            random_btn.setOnClickListener{
                mActivity.changeFragment(2, num)
            }

        }
    }

