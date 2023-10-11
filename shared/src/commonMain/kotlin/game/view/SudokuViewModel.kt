package game.view

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SudokuViewModel {

    private val initialBoard: List<List<SudokuCell>> = generateInitialBoard()
    private val _sudokuBoard = MutableStateFlow(SudokuBoard(initialBoard))
    val sudokuBoard: StateFlow<SudokuBoard> = _sudokuBoard.asStateFlow()

    private val _selectedCell = MutableStateFlow<Pair<Int, Int>?>(null)
    val selectedCell: StateFlow<Pair<Int, Int>?> = _selectedCell.asStateFlow()

    private val _wrongCells = MutableStateFlow<Set<Pair<Int, Int>>>(emptySet())
    val wrongCells: StateFlow<Set<Pair<Int, Int>>> = _wrongCells.asStateFlow()

    private val _correctCells = MutableStateFlow<Set<Pair<Int, Int>>>(emptySet())
    val correctCells: StateFlow<Set<Pair<Int, Int>>> = _correctCells.asStateFlow()

    private var _checkAttempts = MutableStateFlow(0)
    val checkAttempts: StateFlow<Int> = _checkAttempts.asStateFlow()
    private val maxCheckAttempts = 3

    fun selectCell(row: Int, col: Int) {
        if (_sudokuBoard.value.getCell(row, col).isGiven == false) {
            _selectedCell.value =
                if (_selectedCell.value == Pair(row, col)) null else Pair(row, col)
        }
    }

    fun updateSelectedCell(value: Int?) {
        _selectedCell.value?.let { (row, col) ->
            if (value != null) {
                _sudokuBoard.value = _sudokuBoard.value.setCell(row, col, value)
                // Remove the cell from the wrongCells set if it's updated by the user
                _wrongCells.value = _wrongCells.value.minus(Pair(row, col))
            } else {
                _sudokuBoard.value = _sudokuBoard.value.clearCell(row, col)
                // Also remove the cell from the wrongCells set if it's cleared by the user
                _wrongCells.value = _wrongCells.value.minus(Pair(row, col))
            }
        }
    }

    fun clearUserCells() {
        val clearedBoard = _sudokuBoard.value.board.map { row ->
            row.map { cell ->
                if (cell.isGiven) cell else SudokuCell(null)
            }
        }
        _sudokuBoard.value = SudokuBoard(clearedBoard)
    }

    fun checkValues() {
        if (checkAttempts.value >= maxCheckAttempts) {
            // If the user has already checked 3 times, don't proceed further
            return
        }

        val wrongCells = mutableSetOf<Pair<Int, Int>>()
        val correctCells = mutableSetOf<Pair<Int, Int>>()
        // Here, you'd compare _sudokuBoard.value with the solution.
        // If a cell doesn't match, it's not a given cell, and it's not empty, add its coordinates to wrongCellsSet.
        val solution = generateSolutionBoard() // This function should return the correct solution
        for (i in 0..8) {
            for (j in 0..8) {
                val cell = _sudokuBoard.value.getCell(i, j)
                if (!cell.isGiven && cell.value != null) {
                    if(cell.value == solution[i][j])
                        correctCells.add(Pair(i, j))
                    else
                        wrongCells.add(Pair(i, j))
                }
            }
        }
        _wrongCells.value = wrongCells
        _correctCells.value = correctCells

        // Increment the checkAttempts counter
        _checkAttempts.value++

    }
}

