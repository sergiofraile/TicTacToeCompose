package com.sergiofraile.tictactoecompose.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.sergiofraile.tictactoecompose.models.Game
import com.sergiofraile.tictactoecompose.models.Player
import com.sergiofraile.tictactoecompose.ui.components.SquareView

@Composable
fun GameScreen() {
    val game = remember { Game(3) }

    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You are playing as ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = Player.Person.visualCue(),
                style = TextStyle(
                    color = Player.Person.color(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
        Column {
            for (i in 0 until game.boardSquareSize) {
                Row {
                    for (j in 0 until game.boardSquareSize) {
                        val position = (i * game.boardSquareSize) + j
                        SquareView(
                                dataSource = game.board[position],
                                modifier = Modifier.padding(4.dp),
                                action = { id: Int ->
                                    game.didTapOnSquare(id)
                                }
                        )
                    }
                }
            }
        }
        Button(onClick = { game.resetGame() }) {
            Text("Restart")
        }

        if (game.isGameOver) {
            presentDialog(
                message = if (game.currentPlayer == Player.Person) "You win!! 🥳" else "You lose 😢",
                onClose = { game.resetGame() }
            )
        }
    }
}

@Composable
fun presentDialog(message: String, onClose: ()->Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text("Game over")
        },
        text = {
            Text(message)
        },
        buttons = {
            Button(onClick = onClose) {
                Text("OK")
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameScreen()
}