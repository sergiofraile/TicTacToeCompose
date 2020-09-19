package com.sergiofraile.tictactoecompose.models

import androidx.compose.runtime.*

data class Game(val boardSquareSize: Int) {
    var board = mutableStateListOf<Square>()
        private set
    var isGameOver by mutableStateOf(false)
    var currentPlayer = Player.Person

    init {
        setupBoard()
    }

    private fun setupBoard() {
        val boardSize = boardSquareSize * boardSquareSize
        for (i in 0..boardSize) {
            val newSquare = Square(i)
            // A small trick to mute the state
            if (i >= board.size) {
                board.add(newSquare)
            } else {
                board[i] = newSquare
            }
        }
    }

    fun resetGame() {
        isGameOver = false
        setupBoard()
    }

    private fun finishPlayerTurn() {
        currentPlayer = when (currentPlayer) {
            Player.Machine -> Player.Person
            Player.Person -> Player.Machine
        }
    }

    fun didTapOnSquare(id: Int) {
        val squareIndex = board.indexOfFirst { it.id == id }
         if (squareIndex >= 0 && board[squareIndex].status != SquareStatus.Filled) {
             val newSquare = Square(id)
             newSquare.player = currentPlayer
             board[squareIndex] = newSquare
             checkIsGameOver()
             if (!isGameOver) {
                 finishPlayerTurn()
             }
         }
    }

    private fun checkIsGameOver() {
        isGameOver = checkColumns() || checkRows() || checkDiagonals()
    }

    private fun checkColumns(): Boolean {
        for (i in 0 until boardSquareSize) {
            val plays = mutableListOf<Player?>()
            for (j in 0 until boardSquareSize) {
                val position = (j * boardSquareSize) + i
                plays.add(board[position].player)
            }

            if (evalWinner(plays = plays.toList())) {
                return true
            }
        }
        return false
    }

    private fun checkRows(): Boolean {
        for (i in 0 until boardSquareSize) {
            val plays = mutableListOf<Player?>()
            for (j in 0 until boardSquareSize) {
                val position = (i * boardSquareSize) + j
                plays.add(board[position].player)
            }

            if (evalWinner(plays = plays.toList())) {
                return true
            }
        }
        return false
    }

    private fun checkDiagonals(): Boolean {
        var plays = mutableListOf<Player?>()

        // First diagonal
        for (i in 0 until boardSquareSize) {
            val position = (i * boardSquareSize) + i
            plays.add(board[position].player)
        }

        if (evalWinner(plays = plays.toList())) {
            return true
        }

        // Second diagonal
        plays = mutableListOf()

        for (i in 0 until boardSquareSize) {
            val position = (i * boardSquareSize) + (boardSquareSize - 1 - i)
            plays.add(board[position].player)
        }

        return(evalWinner(plays = plays.toList()))
    }

    private fun evalWinner(plays: List<Player?>): Boolean {
        var lastPlay: Player? = null
        var counter = 0

        for (play in plays) {
            if (lastPlay != null && lastPlay == play) {
                counter++
            }
            lastPlay = play
        }

        return (counter == (boardSquareSize - 1))
    }
}
