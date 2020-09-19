package com.sergiofraile.tictactoecompose.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Square(val id: Int) {
    var status by mutableStateOf(SquareStatus.Empty)
    var player: Player? = null
        set(value) {
            field = value
            status = SquareStatus.Filled
        }
}