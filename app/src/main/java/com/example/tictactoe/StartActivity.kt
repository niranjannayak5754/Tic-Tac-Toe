package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnStartGame.setOnClickListener() {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("player1", etPlayer1.text.toString())
                intent.putExtra("player2", etPlayer2.text.toString())
                startActivity(intent)
                finish()
        }
    }
}