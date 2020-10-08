package com.example.poemsactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.preference.PreferenceManager
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var isRunning:Boolean = false
    var i :Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /**toolbar 설정**/
        val toolbar = findViewById(R.id.toolbar1) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)



        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        /**
         * 시집 목록에서 저장된 시 제목, 시인, 내용을 꺼내와서 이를 각 항목에 맞게 출력해준다.
         * **/
        val title = pref.getString("title","")
        val writer = pref.getString("writer","")
        val content = pref.getString("content","")

        showToast(title.toString()+"를 클릭하셨습니다.")

        var img1: Array<Int> = arrayOf(R.drawable.image1_1,R.drawable.image1_2,R.drawable.image1_3)
        var img2: Array<Int> = arrayOf(R.drawable.image2_1,R.drawable.image2_2,R.drawable.image2_3)
        var temp: Array<Int>

        result_title.setText(title)
        result_writer.setText(writer)
        result_content.setText(content)

        /**
         * equals 메소드를 이용하여 제목에 해당하는 이미지 리스트를 하단에 정의한 클래스에 할당
         * **/
        if(title.equals("별 헤는 밤")) {
            isRunning = true
            temp = img1
            val t1 = ThreadClass(img1)
            t1.start()

        }else {
            isRunning = true
            temp = img2
            val t2 = ThreadClass(img2)
            t2.start()

        }

        /**
         * 사용자가 이미지를 선택할 떄 해당 이미지가 담겨져 있는 ArrayList 객체와 id값을 의미하는 i변수 값을 intent한다.
         * **/

        imageView.setOnClickListener{



            Log.d("TAG", "onCreate: "+i)

            val intent = Intent(this,imageResult::class.java)
            intent.putExtra("image_array",temp)
            intent.putExtra("image_id",i)

            startActivity(intent)
        }

    }


    fun showToast(content:String){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
    }

    inner class ThreadClass(img:Array<Int>):Thread(){
        var img = img


        override fun run(){
            while(isRunning){

                runOnUiThread{
                    if(i<3) {
                        imageView.setImageResource(img.get(i))
                    }else {
                        i = 0
                        imageView.setImageResource(img.get(i))
                    }
                }
                SystemClock.sleep(1500)
                i++

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }
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


