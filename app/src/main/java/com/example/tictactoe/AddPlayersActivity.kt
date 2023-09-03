package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.databinding.ActivityAddPlayersBinding

class AddPlayersActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPlayersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_players)

        binding.startGame.setOnClickListener {
            val playerOneName = binding.playerOneName.text.toString()
            val playerTwoName = binding.playerTwoName.text.toString()

            if (playerOneName.isEmpty() || playerTwoName.isEmpty()) {
                Toast.makeText(this, "Please enter the player names", Toast.LENGTH_LONG)
            }
            else {
                Intent(this@AddPlayersActivity, MainActivity::class.java).also {
                    it.putExtra("playerOne", playerOneName)
                    it.putExtra("playerTwo", playerTwoName)
                    startActivity(it)
                }
            }
        }
    }
}