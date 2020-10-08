package com.example.poemsactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var intent: Intent

        /**
         * 제목 버튼을 클릭할시 하단에 정의한 메소드 호출과 시제목, 시인, 내용을 파라미터를 넘겨준다.
         * **/
        poemtitle_btn1.setOnClickListener{
            saveInformation(poemtitle_btn1.text.toString(),premswriter_textview1.text.toString(),premscontent_textview1.text.toString())
            moveResult()
        }
        poemtitle_btn2.setOnClickListener{
            saveInformation(poemtitle_btn2.text.toString(),poemswriter_textview2.text.toString(),poemscontent_textview2.text.toString())
            moveResult()
        }
    }

    /**
     * 사용자가 선택한 시에 대한 제목, 시인, 내용을 xml형식에 파일에 저장하는 SharedPreferences를 이용
     * **/
    fun saveInformation(title:String, writer:String, content:String){ //파일에 사용자가 선택한 시 정보를 저장
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("title",title).putString("writer",writer).putString("content",content).apply();
    }
    
    /**
     * 각 시 마다 다음으로 이동하는 activity는 같아 해당 메소드를 구현 하였음.
     * 각 시제목 클릭이벤트 리스너를 위에서 구현후 해당 메소드를 호출하는 방식으로 activity 이동 
     * **/
    fun moveResult(){ //엑티비티 이동기능을 담당하는 함수
        intent = Intent(this,ResultActivity::class.java)
        startActivity(intent)
    }

}
