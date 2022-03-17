package com.example.bbssample

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class addFoodList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var locationPermission: ActivityResultLauncher<Array<String>> // 권한 허가

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_list)

        val cameraBtn = findViewById<ImageButton>(R.id.cameraBtn)
        val albumBtn = findViewById<ImageButton>(R.id.photoBtn)

        cameraBtn.setOnClickListener {
            locationPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            }
        }
        albumBtn.setOnClickListener {
            
        }
    }

}