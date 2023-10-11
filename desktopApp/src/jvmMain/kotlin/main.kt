import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = "Sudoku",
        icon = painterResource("assets/mine.png"),
        state = windowState
    ) {
        MainView(windowState)
    }
}