package com.example.tictactoe

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.dialogbox.*
import kotlin.math.log

class GameActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val player1Name = intent.getStringExtra("player1")
        val player2Name = intent.getStringExtra("player2")

        tvGameBattle.text ="$player1Name v/s $player2Name"
        tvPlayer.text = "$player1Name's Turn"

//        calling function onClick whenever the button is clicked

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)

        btnNewGame.setOnClickListener(){
            finish()
            val newGame = Intent(this, StartActivity::class.java)
            startActivity(newGame)
        }
    }
    private var counter =1
    private val player1 = ArrayList<Int>()
    private val player2 = ArrayList<Int>()

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?){
//      getting player names
        val player1Name = intent.getStringExtra("player1")
        val player2Name = intent.getStringExtra("player2")

        val btnClicked = view as Button
        val cellId = btnClicked.tag.toString().toInt()

        if(counter%2!=0){
            btnClicked.text = "X"
            btnClicked.setBackgroundResource(R.drawable.player1btn)
            player1.add(cellId)

                if(counter>4 && checkPlayer1Win()) {
                    val show = Dialog(this)
                    show.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    show.setCancelable(false)
                    show.setContentView(R.layout.dialogbox)
                    show.tvWinner.text="Congratulations!!!\n$player1Name won the game"
                    show.btnExit.setOnClickListener(){
                        finish()
                    }
                    show.btnPlayAgain.setOnClickListener(){
                        finish()
                        val intent = Intent(this,GameActivity::class.java)
                        intent.putExtra("player1",player1Name)
                        intent.putExtra("player2", player2Name)
                        startActivity(intent)
                   }
                    show.show()
                    return
                }
            tvPlayer.text = "$player2Name's Turn"
            tvPlayer.setBackgroundResource(R.drawable.player2btn)
        }
        else{
            btnClicked.text ="0"
            btnClicked.setBackgroundResource(R.drawable.player2btn)
            player2.add(cellId)

                if(counter>4 && checkPlayer2Win()) {
                    val show = Dialog(this)
                    show.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    show.setCancelable(false)
                    show.setContentView(R.layout.dialogbox)
                    show.tvWinner.text="Congratulations!!!\n$player2Name won the game"
                    show.btnExit.setOnClickListener(){
                        finish()
                    }
                    show.btnPlayAgain.setOnClickListener(){
                        val intent = Intent(this,GameActivity::class.java)
                        intent.putExtra("player1",player1Name)
                        intent.putExtra("player2", player2Name)
                        startActivity(intent)
                    }
                    show.show()
                    return
                }
            tvPlayer.text = "$player1Name's Turn"
            tvPlayer.setBackgroundResource(R.drawable.player1btn)
        }
        btnClicked.isEnabled = false
        if (counter==9) {
            val show = Dialog(this)
            show.requestWindowFeature(Window.FEATURE_NO_TITLE)
            show.setCancelable(false)
            show.setContentView(R.layout.dialogbox)
            show.tvWinner.text="That was tough to decide Winner\nGame is Draw"
            show.btnExit.setOnClickListener(){
                finish()
            }
            show.btnPlayAgain.setOnClickListener(){
                finish()
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("player1",player1Name)
                intent.putExtra("player2", player2Name)
                startActivity(intent)
            }
            show.show()
            return
        }
        counter++
    }
    private fun checkPlayer1Win(): Boolean {
        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            return true
        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            return true
        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
            return true
        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
            return true
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            return true
        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            return true
        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
            return true
        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
            return true
        return  false
    }

    private fun checkPlayer2Win():Boolean{
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            return true
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            return true
        if (player2.contains(3) && player2.contains(6) && player2.contains(9))
            return true
        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
            return true
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            return true
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            return true
        if (player2.contains(3) && player2.contains(5) && player2.contains(7))
            return true
        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
            return true
        return  false
    }
}
