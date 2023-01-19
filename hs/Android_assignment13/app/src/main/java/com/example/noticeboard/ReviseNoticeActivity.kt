package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.noticeboard.databinding.AddNoticeBinding
import com.example.noticeboard.databinding.ReviseNoticeBinding

class ReviseNoticeActivity: AppCompatActivity() {

    private lateinit var binding: ReviseNoticeBinding
    private lateinit var notice: NoticeData
    private var pictureUri : Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReviseNoticeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val revisePicture = binding.pictureReviseBtn
        val delPicture = binding.pictureDeleteBtn
        val addBtn = binding.noticeReviseBtn
        val returnBtn = binding.returnBtn
        val inputTitle = binding.reNoticeTitle
        val inputContent = binding.reNoticeContent
        val inputName = binding.reNoticeWriter
        val num = intent.getIntExtra("위치", 0)

        notice =  CombineViewModel.viewModel.getData(num)
        binding.data = notice

        revisePicture.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }

        delPicture.setOnClickListener{
            pictureUri = R.drawable.whihe_background.getResourceUri(this)
            Glide.with(this)
                .load(R.drawable.ic_launcher_background.getResourceUri(this))
                .override(5000,5000)
                .fitCenter()
                .into(binding.reImageContent)
        }

        addBtn.setOnClickListener{
            if (pictureUri == null)
            {
                pictureUri = notice.uri
            }
            notice = NoticeData(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString(), pictureUri)
            CombineViewModel.viewModel.reviseData(num, notice)
            finish()
        }

        returnBtn.setOnClickListener{
            finish()
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK && it.data != null){
            var uri = it.data!!.data
            pictureUri = uri
            Glide.with(this)
                .load(uri)
                .override(5000,5000)
                .fitCenter()
                .into(binding.reImageContent)
        }
    }

    private fun Int.getResourceUri(context: Context): Uri? {
        return context.resources.let {
            Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(it.getResourcePackageName(this))		// it : resources, this : ResId(Int)
                .appendPath(it.getResourceTypeName(this))		// it : resources, this : ResId(Int)
                .appendPath(it.getResourceEntryName(this))		// it : resources, this : ResId(Int)
                .build()
        }
    }
}