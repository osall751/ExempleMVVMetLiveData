package sn.set.exemples.exemplemvvm01

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var jwt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jwt = intent.getStringExtra("jwt")!!
        var txtV = findViewById<TextView>(R.id.txtHello)
        txtV.text = " Le Token est " + jwt
    }
}