import androidx.compose.ui.window.ComposeUIViewController
import game.view.SudokuBoardView
import game.view.SudokuViewModel

actual fun getPlatformName(): String = "iOS"

val viewModel = SudokuViewModel()
fun MainViewController() = ComposeUIViewController { SudokuBoardView(viewModel) }