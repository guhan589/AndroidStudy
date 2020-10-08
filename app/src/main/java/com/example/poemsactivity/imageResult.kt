package com.example.poemsactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_image_result.*

class imageResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_result)

        /**toolbar 설정**/
        val toolbar = findViewById(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)



        /**
         * 이미지 ArrayList와 사용자가 클릭한 이미지 순서를 받고 이를 이용하여 이미지뷰에 이미지를 띄운다.
         **/
        val img_array:Array<Int> = intent.getSerializableExtra("image_array") as Array<Int>
        val id = intent.getIntExtra("image_id",0)

        if (img_array != null) {
            detail_ImageView.setImageResource(img_array.get(id))
        }else
            Toast.makeText(this,"이미지 불러오기 오류",Toast.LENGTH_SHORT).show()


    }

    /**
     * 툴바에 뒤로가기 버튼을 누를시 현재 acitivity를 finish한다.
     * **/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}