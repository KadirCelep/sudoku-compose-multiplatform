package game.view
data class SudokuCell(
    val value: Int?, // null means the cell is empty
    val isGiven: Boolean = false // true if it's an initial number
)

data class SudokuBoard(internal val board: List<List<SudokuCell>>) {

    fun getCell(row: Int, col: Int): SudokuCell = board[row][col]

    fun setCell(row: Int, col: Int, value: Int?): SudokuBoard {
        val newBoard = board.mapIndexed { rowIndex, rowList ->
            rowList.mapIndexed { colIndex, cell ->
                when {
                    rowIndex == row && colIndex == col && !cell.isGiven -> SudokuCell(value)
                    else -> cell
                }
            }
        }
        return SudokuBoard(newBoard)
    }

    fun clearCell(row: Int, col: Int): SudokuBoard {
        val newBoard = board.mapIndexed { rowIndex, rowList ->
            rowList.mapIndexed { colIndex, cell ->
                when {
                    rowIndex == row && colIndex == col && !cell.isGiven -> SudokuCell(null)
                    else -> cell
                }
            }
        }
        return SudokuBoard(newBoard)
    }
}