package com.metamom.bbssample.recipe2

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.StrictMode

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.metamom.bbssample.R
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class RecipeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recimain)

        val listView = findViewById<ListView>(R.id.reciView)
        val editText = findViewById<EditText>(R.id.reciEdit)
        val imageView = findViewById<ImageView>(R.id.imageView)

        // 레시피 가져오기

       var dto = RecipeDto(0, "", "", "", "", "", "", "")
        var recipeList = RecipeDao.getInstance().getRecipe(dto)
////        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + recipeList?.get(0))

        // list에 RecipeDto recipeName으로 출력
        var list :  ArrayList<String> = ArrayList<String>()
        for(i in 0 until recipeList!!.size){
            recipeList[i].recipeName?.let { list.add(it) }
        }


/*        var list :  ArrayList<String> = ArrayList<String>()
        for(i in 0 until recipeList!!.size){
            recipeList[i].recipeName?.let { list.add(it) }
        }*/

        var adapter =  ArrayAdapter(this, R.layout.recipe_list, list)
        listView.adapter = adapter

        // listview 검색기능!
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable) {

                dto = RecipeDto(0, edit.toString(), "", "", "", "", "", "")
                var recipeList = RecipeDao.getInstance().getRecipe( dto)
//                println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + recipeList?.get(0))

        // list에 RecipeDto recipeName으로 출력

                var list :  ArrayList<String> = ArrayList<String>()
                for(i in 0 until recipeList!!.size){
                    recipeList[i].recipeName?.let { list.add(it) }
                }
/*

                var adapter =  ArrayAdapter(this, R.layout.recipe_list, list)
                //var adapter =  ArrayAdapter(this, R.layout.recipe_list, list)
                listView.adapter = adapter

*/
                val filterText = edit.toString()
                if (filterText.length > 0) {
                    listView.setFilterText(filterText)
                } else {
                    listView.clearTextFilter()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })



        // bottomSheet
        val bottomSheetView = layoutInflater.inflate(R.layout.activity_recipe_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        val reciDetailButton = bottomSheetView.findViewById<Button>(R.id.reciBotBtn)
        val reciBotImage = bottomSheetView.findViewById<ImageView>(R.id.reciBotImage)
        val reciBotTitle = bottomSheetView.findViewById<TextView>(R.id.reciBotTitle)
        val reciBotcontent = bottomSheetView.findViewById<TextView>(R.id.reciBotContent)


        // bottomSheet에 가져간 데이터 짐싸기
        var choicePos = 0

        reciDetailButton.setOnClickListener {

            Toast.makeText(this, "${recipeList[choicePos].recipeName} 클릭", Toast.LENGTH_SHORT).show()

            // 이동
            //Intent(이동) +  putExtra(짐)
            val intent = Intent(this, DetailRecipe::class.java)
            intent.putExtra("List", recipeList[choicePos])
            intent.putExtra("Num", choicePos)
            startActivity(intent)
        }


        // listView 눌려서 bottomSheet 가져오기
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, v: View?, pos: Int, p3: Long) {
                reciBotTitle.text = recipeList[pos].recipeName

                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~" +  recipeList[pos].recipeName);

                reciBotImage.setImageBitmap(ImageRoader().getBitmapImg(recipeList[pos].foodImage))

                reciBotcontent.text = recipeList[pos].brief
                bottomSheetDialog.show()
                choicePos = pos

                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~" + choicePos)
            }
        }
    }
}


// 서버에서 이미지 가져오기
class ImageRoader {
    private val serverUrl = "http://14.39.38.168:3000/upload/"

    init {
        ThreadPolicy()
    }

    fun getBitmapImg(imgStr: String?): Bitmap? {
        var bitmapImg: Bitmap? = null
        try {
            val url = URL(
                serverUrl +
                        URLEncoder.encode(imgStr, "utf-8")
            )
            // Character is converted to 'UTF-8' to prevent broken
            val conn = url
                .openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            val `is` = conn.inputStream
            bitmapImg = BitmapFactory.decodeStream(`is`)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmapImg
    }
}

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
class ThreadPolicy {
    // For smooth networking
    init {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}