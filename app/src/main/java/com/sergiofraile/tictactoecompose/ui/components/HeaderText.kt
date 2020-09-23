package com.sergiofraile.tictactoecompose.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sergiofraile.tictactoecompose.models.Player

@Composable
fun HeaderText() {
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
}