package com.example.assignment_10

import android.Manifest
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
   //val print_num = findViewById<TextView>(R.id.print_number)
    // 권한을 허락할지 묻는다
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> getAudioFile()
                else -> {
                    when (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        true -> permissionDialog(true) // 이번에만 거부
                        else -> permissionDialog(false) // 거부후 다신 안물어보기
                    }
                }
            }
        }

    //설정앱 부분을 열어줌
    private val openSettings =
        registerForActivityResult(OpenSettings()) {
            initView()
            hidePermissionSettingsButton()
        }

    private val dataList = mutableListOf<MusicData>()
    private val musicData = MusicData()
    private val musicAdapter = MusicAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var permissionLayout: LinearLayout
    var mediaPlayer: MediaPlayer?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emptyTextView = findViewById(R.id.empty_text_view)
        recyclerView = findViewById(R.id.recycler_view)
        permissionLayout = findViewById(R.id.permission_layout)
        val permissionSettingButton: Button = findViewById(R.id.permission_settings_button)
        var artistName: TextView = findViewById(R.id.text_artist)
        var titleName: TextView = findViewById(R.id.text_name)

        initView()

        // permissionSettingButton 버튼을 누르면  설정 앱을 킨다.
        permissionSettingButton.setOnClickListener {
            openSettings.launch(null)
        }

        musicAdapter.setItemClickListener(object: MusicAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                var musicUri: Uri? = null
                musicUri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,  dataList[position].id)
                artistName.text = dataList[position].artist
                titleName.text = dataList[position].title
                if(mediaPlayer != null)
                {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(v.context, musicUri)
                mediaPlayer?.start()
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    @RequiresApi(Build.VERSION_CODES.M)

    //허가 다이로그를 출력
    private fun permissionDialog(isDeniedOnce: Boolean) {
        when (isDeniedOnce) {
            true -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_permission_title))
                    .setMessage(getString(R.string.dialog_permission_messsage))
                    .setPositiveButton(getString(R.string.dialog_permission_ok)) { _, _ ->
                        checkPermission()
                    }
                    .setNegativeButton(getString(R.string.dialog_permission_cancel)) { dialog, _ ->
                        dialog.dismiss()
                        showPermissionSettingsButton()
                    }
                    .setCancelable(false)
                builder.show()
            }
            // 거부를 눌렀을 경우
            else -> showPermissionSettingsButton() // 허락 버튼을 보여주는 뷰 생성
        }
    }

    // permissionLayout을 보여줌
    private fun showPermissionSettingsButton() {
        recyclerView.visibility = View.GONE
        emptyTextView.visibility = View.GONE
        permissionLayout.visibility = View.VISIBLE
    }

    // recyclerView를 보여줌
    private fun hidePermissionSettingsButton() {
        recyclerView.visibility = View.VISIBLE
        permissionLayout.visibility = View.GONE
    }

    private fun getAudioFile() {
        // 필요한 데이터 리스트로 저장
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION
        )

        //버전별로 정렬방법 변환
        val sortOrder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) //R은 안드로이드 11
        {
            //11 이상은 Files를 통해 정렬
            "${MediaStore.Files.FileColumns.ALBUM}, ${MediaStore.Files.FileColumns.ARTIST}, CAST(${MediaStore.Files.FileColumns.CD_TRACK_NUMBER} AS INTEGER)"
        } else {
            // 이하는 Audio를 통해 정렬
            " ${MediaStore.Audio.AlbumColumns.ALBUM}, ${MediaStore.Audio.AlbumColumns.ARTIST}, CAST(${MediaStore.Audio.AudioColumns.TRACK} AS INTEGER)"
        }

        /*
        커서를 이용하여 데이터 사용
         */
        val cursor = this.contentResolver.query( // contentResolver의 커리라는 기능을 통해 커서를 받는다.
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            MediaStore.Audio.Media.IS_MUSIC,
            null,
            sortOrder
        )

        cursor?.use {
            val idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val albumIdColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
            val titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)

            while (cursor.moveToNext()) //순서상 다음 차례로 넘어감
            {
                val id = cursor.getString(idColumn)
                val albumId = cursor.getLong(albumIdColumn)
                val title = cursor.getString(titleColumn)
                val artist = cursor.getString(artistColumn)
                val duration = cursor.getLong(durationColumn)

                val albumUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    "${MediaStore.Audio.Media.EXTERNAL_CONTENT_URI}/$id"
                } else {
                    "content://media/external/audio/albumart/$albumId"
                }

                // 데이터를 한번에 묶어서 리스트에 넣음
                dataList.add(MusicData(id, title, artist, duration, albumUri))
                musicAdapter.notifyItemInserted(musicAdapter.itemCount)
            }
        }
        checkIsMusicEmpty() // 음악이 없으면 탈출
    }

    // 음악이 있는 경우와 없는 경우에 따른 뷰를 결정
    private fun checkIsMusicEmpty() {
        if (musicAdapter.itemCount != 0) {
            emptyTextView.visibility = View.GONE
        } else {
            emptyTextView.visibility = View.VISIBLE
        }
    }

    //초기 뷰 설정
    private fun initView() {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager(this).orientation
        )

        musicAdapter.dataList = dataList
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter
            addItemDecoration(dividerItemDecoration)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission()
        } else {
            getAudioFile()
        }
    }
}