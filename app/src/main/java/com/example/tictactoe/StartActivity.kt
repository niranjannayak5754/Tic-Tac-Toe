package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.GameActivity.Companion.KEY_1
import com.example.tictactoe.GameActivity.Companion.KEY_2
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        etPlayer1.requestFocus()
        btnStartGame.setOnClickListener() {
            val player1 = etPlayer1.text.toString()
            val player2 = etPlayer2.text.toString()
            if (player1.isNotEmpty() && player2.isNotEmpty()) {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra(KEY_1, player1)
                intent.putExtra(KEY_2, player2)
                startActivity(intent)
                finish()
            } else if (player1.isEmpty())
                etPlayer1.requestFocus()
            else
                etPlayer2.requestFocus()
        }
    }
}