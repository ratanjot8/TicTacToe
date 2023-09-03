package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // most optimal logic used for handling tic-tac-toe operations
    private lateinit var binding: ActivityMainBinding
    private var boxPositions = mutableListOf(
        mutableListOf(0, 0, 0),
        mutableListOf(0, 0, 0),
        mutableListOf(0, 0, 0)
    )
    private var row = mutableListOf(0, 0, 0)
    private var column = mutableListOf(0, 0, 0)
    private var leftDiagonal = 0
    private var rightDiagonal = 0
    private var winnerName = ""
    private var playerTurn = 1
    private var totalSelectedBoxes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.playerOneName.text = intent.getStringExtra("playerOne")
        binding.playerTwoName.text = intent.getStringExtra("playerTwo")
        initialiseClickListeners()
    }

    private fun initialiseClickListeners() {
        binding.image1.setOnClickListener {
            if (isBoxSelectable(0, 0)) {
                performAction(it as ImageView, 0, 0)
            }
        }
        binding.image2.setOnClickListener {
            if (isBoxSelectable(0, 1)) {
                performAction(it as ImageView, 0, 1)
            }
        }
        binding.image3.setOnClickListener {
            if (isBoxSelectable(0, 2)) {
                performAction(it as ImageView, 0, 2)
            }
        }
        binding.image4.setOnClickListener {
            if (isBoxSelectable(1, 0)) {
                performAction(it as ImageView, 1, 0)
            }
        }
        binding.image5.setOnClickListener {
            if (isBoxSelectable(1, 1)) {
                performAction(it as ImageView, 1, 1)
            }
        }
        binding.image6.setOnClickListener {
            if (isBoxSelectable(1, 2)) {
                performAction(it as ImageView, 1, 2)
            }
        }
        binding.image7.setOnClickListener {
            if (isBoxSelectable(2, 0)) {
                performAction(it as ImageView, 2, 0)
            }
        }
        binding.image8.setOnClickListener {
            if (isBoxSelectable(2, 1)) {
                performAction(it as ImageView, 2, 1)
            }
        }
        binding.image9.setOnClickListener {
            if (isBoxSelectable(2, 2)) {
                performAction(it as ImageView, 2, 2)
            }
        }
    }

    private fun performAction(imageView: ImageView, selectedRow: Int, selectedColumn: Int) {
        boxPositions[selectedRow][selectedColumn] = playerTurn
        if (playerTurn == 1) {
            setPlayerOne(imageView, selectedRow, selectedColumn)
        }
        else {
            setPlayerTwo(imageView, selectedRow, selectedColumn)
        }
        if (winnerName.isNotBlank()) {
            val finishDialog = FinishDialog(
                this@MainActivity,
                "$winnerName has won the match",
                this@MainActivity)
            finishDialog.show()
        }
        else if (totalSelectedBoxes == 9) {
            val finishDialog = FinishDialog(
                this@MainActivity,
                "It is a draw!",
                this@MainActivity)
            finishDialog.show()
        }
        else {
            togglePlayerTurn()
            totalSelectedBoxes++
        }
    }

    private fun setPlayerOne(imageView: ImageView, selectedRow: Int, selectedColumn: Int) {
        row[selectedRow]++
        column[selectedColumn]++
        if(selectedRow == selectedColumn) {
            leftDiagonal++
        }
        if ((selectedRow + selectedColumn) == (3-1)) {
            rightDiagonal++
        }
        imageView.setImageResource(R.drawable.cross)
        if (row[selectedRow] == 3 || column[selectedColumn] == 3 || leftDiagonal == 3 || rightDiagonal == 3) {
            winnerName = binding.playerOneName.text.toString()
        }
    }

    private fun setPlayerTwo(imageView: ImageView, selectedRow: Int, selectedColumn: Int) {
        row[selectedRow]--
        column[selectedColumn]--
        if(selectedRow == selectedColumn) {
            leftDiagonal--
        }
        if ((selectedRow + selectedColumn) == (3-1)) {
            rightDiagonal--
        }
        imageView.setImageResource(R.drawable.circle)
        if (row[selectedRow] == -3 || column[selectedColumn] == -3 || leftDiagonal == -3 || rightDiagonal == -3) {
            winnerName = binding.playerTwoName.text.toString()
        }
    }

    private fun togglePlayerTurn() {
        playerTurn = if (playerTurn == 1) 2 else 1
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.round_black_blue_border)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue)
        }
        else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.round_black_blue_border)
            binding.playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue)
        }
    }

    private fun isBoxSelectable(selectedRow: Int, selectedColumn: Int) =
        boxPositions[selectedRow][selectedColumn] == 0 // return true if boxPosition is 0, false otherwise

    fun restartMatch() {
        boxPositions = mutableListOf(
            mutableListOf(0, 0, 0),
            mutableListOf(0, 0, 0),
            mutableListOf(0, 0, 0)
        )
        row = mutableListOf(0, 0, 0)
        column = mutableListOf(0, 0, 0)
        leftDiagonal = 0
        rightDiagonal = 0
        winnerName = ""
        playerTurn = 1
        totalSelectedBoxes = 1
        binding.image1.setImageDrawable(null)
        binding.image2.setImageDrawable(null)
        binding.image3.setImageDrawable(null)
        binding.image4.setImageDrawable(null)
        binding.image5.setImageDrawable(null)
        binding.image6.setImageDrawable(null)
        binding.image7.setImageDrawable(null)
        binding.image8.setImageDrawable(null)
        binding.image9.setImageDrawable(null)
    }
}