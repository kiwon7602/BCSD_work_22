package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract

/*
우진님의 코드에서는 ActivityResultContract.java로 import된다.
여기서는 .kt로 import되어 override fun createIntent의 파라미터인 input의 자료형이 Unit?이 아닌 Unit으로 온다.
이를 해결하기 위해 ActivityResultContract의 제네릭 파라미터에 Unit?로 변경을 해주었다.
 */
class OpenSettings: ActivityResultContract<Unit?, Int>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        return resultCode
    }
}