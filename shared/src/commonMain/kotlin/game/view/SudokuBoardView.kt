package game.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SudokuBoardView(
    viewModel: SudokuViewModel,
    requestWindowSize: ((width: Dp, height: Dp) -> Unit)? = null
) {
    val boardSize = 9
    val subGridSize = 3
    val sudokuBoard by viewModel.sudokuBoard.collectAsState()
    val selectedCell by viewModel.selectedCell.collectAsState()
    val wrongCells by viewModel.wrongCells.collectAsState()
    val correctCells by viewModel.correctCells.collectAsState()
    val checkAttempts by viewModel.checkAttempts.collectAsState()
    val remainingAttempts = 3 - checkAttempts

    if (requestWindowSize != null) {
        val boardOffset = (16.dp + 16.dp) * 2;

        val width = boardOffset + 280.dp
        val height = boardOffset + 680.dp

        requestWindowSize(width, height)
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Sudoku Grid
            Column {
                for (i in 0 until boardSize) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (j in 0 until boardSize) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        width = if(selectedCell == Pair(i, j)) 2.dp else 1.dp,
                                        color = if(selectedCell == Pair(i, j)) Color.Black else Color.Gray
                                    )
                                    .background(
                                        when {
                                            wrongCells.contains(Pair(i, j)) -> Color.Red.copy(alpha = 0.1f)
                                            correctCells.contains(Pair(i, j)) -> Color.Green.copy(alpha = 0.1f)
                                            (i / subGridSize + j / subGridSize) % 2 == 0 -> Color.LightGray.copy(alpha = 0.2f)
                                            else -> Color.Transparent
                                        }
                                    )
                                    .clickable {
                                        viewModel.selectCell(i, j)
                                    }
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                // Display the Sudoku number if it exists
                                    Text(
                                        text = sudokuBoard.getCell(i, j).value?.toString()?:"",
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = if (sudokuBoard.getCell(i, j).isGiven) FontWeight.Bold else FontWeight.Normal
                                    )
                            }
                        }
                    }
                }
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Number Pad
            GridNumberPadView { number ->
                viewModel.updateSelectedCell(number)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Clear/Reset Board Button
            Button(onClick = { viewModel.clearUserCells() }) {
                Text("Reset Board")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Check Values Button
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { viewModel.checkValues() },
                    enabled = remainingAttempts > 0
                ) {
                    Text("Validate")
                }
            }

            // Optionally, display a message when all attempts are used up
            if (remainingAttempts <= 0) {
                Text("You've used up all your attempts!", color = Color.Red)
            } else {
                Text("Attempts remaining: $remainingAttempts")
            }
        }
    }
}
