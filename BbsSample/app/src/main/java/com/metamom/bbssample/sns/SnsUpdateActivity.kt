package com.metamom.bbssample.sns

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.metamom.bbssample.R
import kotlinx.android.synthetic.main.activity_add_food_list.*
import kotlinx.android.synthetic.main.activity_food_list_meals_update.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class SnsUpdateActivity : AppCompatActivity() {

    //카메라 , 스토리지 권한 변수
    val CAMERA = arrayOf(Manifest.permission.CAMERA)
    val STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    // 카메라 스토리지 정해진 상수값.
    val CAMERA_CODE = 98
    val STORAGE_CODE = 99
    var newImgUri:String = ""
    var imgSet:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sns_update)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray){

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_CODE -> {
                for (grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "카메라 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
            STORAGE_CODE -> {
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "저장소 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun getPath(uri: Uri?):String{
        val projection = arrayOf<String>(MediaStore.Images.Media.DATA)
        val cursor: Cursor =managedQuery(uri,projection,null,null,null)
        startManagingCursor(cursor)
        val columnIndex : Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }
    //다른 권한등록 확인
    fun checkPermission(permissions: Array<out String>, type:Int):Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (permission in permissions){
                if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, permissions, type)
                    return false
                }
            }
        }
        return true
    }
    fun camera(){ //카메라 촬영
        if(checkPermission(CAMERA,CAMERA_CODE) && checkPermission(STORAGE,STORAGE_CODE)){
            val itent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(itent,CAMERA_CODE)
        }
    }
    fun fileSava(fileName:String,mimeType:String , bitmap: Bitmap): Uri?{
        var CV= ContentValues()

        //mediaStore에 파일명 타입 지정
        CV.put(MediaStore.Images.Media.DISPLAY_NAME,fileName)
        CV.put(MediaStore.Images.Media.MIME_TYPE,mimeType)
        //안전성 검사
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            CV.put(MediaStore.Images.Media.IS_PENDING,1)
        }
        //store에 파일 저장
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,CV)
        if(uri != null){
            var scriptor = contentResolver.openFileDescriptor(uri,"w")
            val fos = FileOutputStream(scriptor?.fileDescriptor)

            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos)
            fos.close()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                CV.clear()

                //IS_PENDING을 초기화해준다 .
                CV.put(MediaStore.Images.Media.IS_PENDING,0)
                contentResolver.update(uri,CV,null,null)
            }
        }
        return uri
    }
    //결과
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //val albumImg3 = findViewById<ImageView>(R.id.albumImg) -> 여기서 선언하지말고  위에서 선언되어있는 albumImg3를 가져와서 대입해야 오류가안남
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                CAMERA_CODE ->{
                    if(data?.extras?.get("data")!=null){
                        val img = data?.extras?.get("data") as Bitmap
                        val uri = fileSava(RandomFileName(), "image/jpeg", img)
                        albumImg3.setImageURI(uri)
                        println("경로: $uri")
                        println("실제 이미지 경로 : " +getPath(uri))
                        newImgUri =  getPath(uri)
                    }
                }
                STORAGE_CODE ->{
                    val uri = data?.data
                    albumImg3.setImageURI(uri)
                    newImgUri =  getPath(uri)
                }
            }
        }
        //다른 화면에서 사용하게.
        fun albumIngMove(){
            albumImg
        }
    }
    //파일명 날짜로 저장
    fun RandomFileName() : String{
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
        return fileName
    }
    //갤러리 가져오기
    fun Album(){
        if(checkPermission(STORAGE,STORAGE_CODE)){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent,STORAGE_CODE)
        }
    }
}