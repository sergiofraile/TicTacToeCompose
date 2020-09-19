package com.sergiofraile.tictactoecompose.models

import androidx.compose.ui.graphics.Color

enum class Player {
    Person,
    Machine;

    fun visualCue(): String {
        return when(this) {
            Person -> "X"
            Machine -> "O"
        }
    }

    fun color(): Color {
        return when(this) {
            Person -> Color.Blue
            Machine -> Color.Red
        }
    }
}