package com.kadircelep.sudoku

import game.view.SudokuViewModel
import game.view.SudokuBoardView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel = SudokuViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SudokuBoardView(viewModel)
        }
    }
}