package game.view
fun generateInitialBoard(): List<List<SudokuCell>> {
    // This is a sample Sudoku board.
    // 0 represents an empty cell, and any number from 1-9 represents a given number.
    val sampleBoard = listOf(
        listOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
        listOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
        listOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
        listOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
        listOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
        listOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
        listOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
        listOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
        listOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )

    return sampleBoard.map { row ->
        row.map { number ->
            if (number != 0) {
                SudokuCell(value = number, isGiven = true)
            } else {
                SudokuCell(value = null, isGiven = false)
            }
        }
    }
}

fun generateSolutionBoard(): List<List<Int>> {
    // This is a mock solution based on the sample Sudoku board provided earlier.
    // In a real-world scenario, you'd want a dynamic solution based on a Sudoku solving algorithm.
    return listOf(
        listOf(5, 3, 4, 6, 7, 8, 9, 1, 2),
        listOf(6, 7, 2, 1, 9, 5, 3, 4, 8),
        listOf(1, 9, 8, 3, 4, 2, 5, 6, 7),
        listOf(8, 5, 9, 7, 6, 1, 4, 2, 3),
        listOf(4, 2, 6, 8, 5, 3, 7, 9, 1),
        listOf(7, 1, 3, 9, 2, 4, 8, 5, 6),
        listOf(9, 6, 1, 5, 3, 7, 2, 8, 4),
        listOf(2, 8, 7, 4, 1, 9, 6, 3, 5),
        listOf(3, 4, 5, 2, 8, 6, 1, 7, 9)
    )
}
