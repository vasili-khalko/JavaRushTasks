package com.javarush.task.task35.task3513;

/**
 * Created by Basillio on 09.06.2018.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {

    private int numberOfEmptyTiles;
    private int score;
    private Move move;


    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency other) {
        int result = Integer.compare(this.numberOfEmptyTiles, other.numberOfEmptyTiles);

        return result == 0 ? Integer.compare(this.score, other.score) : result;
    }
}
