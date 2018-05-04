package io.github.nowakprojects.pwr.ai.lab2csp.queens.domain

import java.lang.StringBuilder
import kotlin.math.abs

class Chessboard(
        val chessboardSize: Int,
        val fields: Array<Array<Chessman>> = Array<Array<Chessman>>
        (chessboardSize, { (0..chessboardSize).map { Chessman.NONE }.toTypedArray() })) {


    fun getColumn(xColumn: Int): Column = Column(fields.map { column -> column[xColumn] }.toTypedArray())

    fun getRow(yRow: Int): Row = Row(fields[yRow])

    fun areDiagonalsEmptyForPoint(xColumn:Int, yRow:Int):Boolean{
        val result = emptyList<Chessman>().toMutableList()
        for(y in 0 until fields.size){
            for(x in 0 until fields[y].size){
                if(abs(x-xColumn) == abs(y-yRow)){
                    result.add(fields[y][x])
                }
            }
        }
        return result.isEmpty();
    }

    fun placeChessman(chessman: Chessman, xColumn: Int, yRow: Int){
        fields[yRow][xColumn] = chessman;
    }


    /**
     * Method to clone the chessboard
     * @return copied chessboard
     */
    fun clone(): Chessboard {
        val newChessBoard = Chessboard.empty(chessboardSize)
        (0 until chessboardSize).forEach { newChessBoard.fields[it] = fields[it].clone() }
        return newChessBoard
    }

    override fun toString(): String {
        return StringBuilder().apply {
            fields.map { row -> row.toList().toString() }.forEach { this.append(it).append("\n") }
        }.toString()
    }

    fun prettyPrint() {
        print(toString())
    }

    companion object {
        fun empty(chessboardSize: Int) = Chessboard(chessboardSize)
    }
}

open class ChessLine(val fields:Array<Chessman>){
    fun prettyPrint(){
        print(toString())
    }

    fun isEmpty(){
        fields.all { it == Chessman.NONE }
    }
}

class Column(fields: Array<Chessman>) : ChessLine(fields){
    override fun toString(): String {
        return "[\n"+ fields.toList().joinToString(",\n") { it.name } +"\n]\n"
    }
}

class Row(fields: Array<Chessman>): ChessLine(fields){
    override fun toString(): String {
        return fields.toList().toString()
    }
}

class Diagonal(fields: Array<Chessman>) :ChessLine(fields){
    override fun toString(): String {
        return "[\n"+fields.toList().mapIndexed { index, chessman ->  getTabForDiagonalIndex(index) + chessman.name}.joinToString(",\n")+"\n]\n"
    }

    fun getTabForDiagonalIndex(index: Int) = (0..index).map { "  " }.joinToString("")

}