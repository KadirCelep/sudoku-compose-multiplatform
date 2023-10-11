import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowState
import game.view.SudokuBoardView
import game.view.SudokuViewModel

actual fun getPlatformName(): String = "Desktop"

val viewModel = SudokuViewModel()
@Composable
fun MainView(windowState: WindowState) = MaterialTheme {
    SudokuBoardView(
        viewModel,
        requestWindowSize = { w, h ->
            windowState.size = windowState.size.copy(width = w, height = h)
        }
    )
}

@Preview
@Composable
fun AppPreview() {
    App()
}