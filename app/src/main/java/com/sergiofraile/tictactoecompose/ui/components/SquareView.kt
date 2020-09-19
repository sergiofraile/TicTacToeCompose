package com.sergiofraile.tictactoecompose.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.sergiofraile.tictactoecompose.models.Player
import com.sergiofraile.tictactoecompose.models.Square
import com.sergiofraile.tictactoecompose.models.SquareStatus
import com.sergiofraile.tictactoecompose.ui.definitions.TicTacToeComposeTheme

@Composable
fun SquareView(
	modifier: Modifier = Modifier,
	size: IntSize = IntSize(80, 80),
	dataSource: Square,
	action: (id: Int) -> Unit,
) {
	val text = dataSource.player?.visualCue() ?: ""
	val textColor = dataSource.player?.color() ?: Color.Black
	val backgroundColor = if (dataSource.player != null) Color.White else Color.Gray

	Surface(modifier = modifier
		.preferredWidth(size.width.dp)
		.preferredHeight(size.height.dp)
		.align(Alignment.CenterVertically)
		.clickable(onClick = {
			action(dataSource.id)
		}),
		color = backgroundColor,
		shape = RoundedCornerShape(4.dp)
	) {
		Text(
			modifier = Modifier
				.size(size.width.dp, size.height.dp)
				.fillMaxHeight()
				.fillMaxWidth()
				.align(Alignment.CenterVertically),
			text = text,
			style = TextStyle(
				color = textColor,
				fontWeight = FontWeight.Bold,
				fontSize = 60.sp,
				textAlign = TextAlign.Center
			)
		)
	}
}

@Preview("Empty Square")
@Composable
fun SquareViewPreviewEmpty() {
	val square = Square(0);
	square.status = SquareStatus.Empty
	TicTacToeComposeTheme() {
		SquareView(
			dataSource = square,
			action = { id: Int -> print("Pressed id is $id") }
		)
	}
}

@Preview("X Filled Square")
@Composable
fun SquareViewPreviewFilledWithX() {
	val square = Square(0);
	square.player = Player.Person
	TicTacToeComposeTheme() {
		SquareView(
			dataSource = square,
			action = { id: Int -> print("Pressed id is $id") }
		)
	}
}

@Preview("O Filled Square")
@Composable
fun SquareViewPreviewFilledWithO() {
	val square = Square(0);
	square.player = Player.Machine
	TicTacToeComposeTheme() {
		SquareView(
			dataSource = square,
			action = { id: Int -> print("Pressed id is $id") }
		)
	}
}