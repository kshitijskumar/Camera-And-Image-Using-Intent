package com.example.usingcamerawithintent

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetImage.setOnClickListener {
            getImage()
        }

        btnCaptureImage.setOnClickListener {
            captureImage()
        }
    }

    private fun getImage(){
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "image/*"
            startActivityForResult(it, 0)
        }
    }

    private fun captureImage(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            try {
                startActivityForResult(it, 1)
            }catch (e: Exception){
                Log.d("MainActivity", e.message.toString())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK){
            val imageUri = data?.data
            ivGetImage.setImageURI(imageUri)
        }else if (requestCode == 1 && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            ivGetImage.setImageBitmap(imageBitmap)
        }
    }

}