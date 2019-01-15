package com.bingo2;

public class BingoMain2 {
    public static void main(String[] args) {
        int bingoLoops = 1000000;

        for (int z = 0; z < bingoLoops; z++) {
            System.out.println(z);
            //Set the size of playing field
            final int gridSize = 5;
            final int numberRange = 75;
            final int ballsInPartOne = 33;
            final int ballsInPartTwo = 5;
            final int ballsInPartThree = 3;

            Bingo2 bingo = new Bingo2();
            int[][] playingFieldArr = new int[gridSize][gridSize];

            //Determine values for playing field
            bingo.populatePlayingField(playingFieldArr, numberRange, gridSize);

            //Print out the playing field
            //bingo.printPlayingField(playingFieldArr, gridSize);

            //PART ONE
            //Drawing balls
            int[] drawPool = new int[numberRange];

            //Set the drawing pool array values to 1 - 75
            for (int n = 0; n < drawPool.length; n++) {
                drawPool[n] = n + 1;
            }

            int drawnBall;

            for (int ball = 0; ball < ballsInPartOne; ball++) {
                //Draw the ball
                drawnBall = bingo.drawBall(drawPool);

                //Check if the drawn ball matches any on the playing field, if yes mark it with '0'
                bingo.replaceMatchesWithX(playingFieldArr, drawnBall);
            }

            //Print results after PART ONE
            //bingo.printPlayingField(playingFieldArr, gridSize);

            //Determine if there is a win, let the user know
            bingo.determineIfWin(1, playingFieldArr, gridSize);

            //PART TWO
            for (int ball = 0; ball < ballsInPartTwo; ball++) {
                //Draw the ball
                drawnBall = bingo.drawBall(drawPool);

                //Check if the drawn ball matches any on the playing field, if yes mark it with '0'
                bingo.replaceMatchesWithX(playingFieldArr, drawnBall);
            }

            //Print results after PART TWO
            //bingo.printPlayingField(playingFieldArr, gridSize);

            //Determine if there is a win, let the user know
            bingo.determineIfWin(2, playingFieldArr, gridSize);

            //PART THREE
            for (int ball = 0; ball < ballsInPartThree; ball++) {
                //Draw the ball
                drawnBall = bingo.drawBall(drawPool);

                //Check if the drawn ball matches any on the playing field, if yes mark it with '0'
                bingo.replaceMatchesWithX(playingFieldArr, drawnBall);
            }

            //Print current results
            //bingo.printPlayingField(playingFieldArr, gridSize);

            //Determine if there is a win, let the user know
            bingo.determineIfWin(3, playingFieldArr, gridSize);
        }
        Bingo2 bingo = new Bingo2();
        bingo.printWinStats();
    }
}
