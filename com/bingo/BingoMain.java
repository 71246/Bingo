package com.bingo;

import java.util.Scanner;

public class BingoMain {
    public static void main(String[] args) {
        System.out.println("Hi! Welcome to BINGO! Here's your ticket:\n");
        Scanner scanner = new Scanner(System.in);
        String userInput;

        //Set the size of playing field
        final int gridSize = 5;
        final int numberRange = 75;
        final int ballsInPartOne = 33;
        final int ballsInPartTwo = 5;
        final int ballsInPartThree = 3;

        Bingo bingo = new Bingo();
        int[][] playingFieldArr = new int[gridSize][gridSize];

        //Determine values for playing field
        bingo.populatePlayingField(playingFieldArr, numberRange, gridSize);

        //Print out the playing field
        bingo.printPlayingField(playingFieldArr, gridSize);

        //PART ONE
        System.out.println("\nPART ONE: CORNERS \nIn this part you will have to get" +
                " all four corners of your playing field!\n"+
                ballsInPartOne+" balls will be drawn.\n"+
                "Commencing part one. Good luck!\n");

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

        System.out.println("Would you like to see the results? (Y/N)");
        userInput = scanner.next();

        if (userInput.equalsIgnoreCase("y")) {
            //Print current results
            bingo.printPlayingField(playingFieldArr, gridSize);

            //Determine if there is a win, let the user know
            bingo.determineIfWin(1, playingFieldArr, gridSize);

            //PART TWO
            System.out.println("PART TWO: DIAGONALS \nIn this part you will have to get" +
                    " both diagonals of your playing field!\n"+
                    ballsInPartTwo+" additional balls will be drawn.\n"+
                    "Commencing part two. Good luck!\n");

            for (int ball = 0; ball < ballsInPartTwo; ball++) {
                //Draw the ball
                drawnBall = bingo.drawBall(drawPool);

                //Check if the drawn ball matches any on the playing field, if yes mark it with '0'
                bingo.replaceMatchesWithX(playingFieldArr, drawnBall);
            }

            System.out.println("Would you like to see the results? (Y/N)");
            userInput = scanner.next();

            if (userInput.equalsIgnoreCase("y")) {
                //Print current results
                bingo.printPlayingField(playingFieldArr, gridSize);

                //Determine if there is a win, let the user know
                bingo.determineIfWin(2, playingFieldArr, gridSize);

                //PART THREE
                System.out.println("PART THREE: FULL FIELD \nIn this part you will have to get" +
                        " ALL REMAINING cells of your playing field!\n" +
                        ballsInPartThree + " additional balls will be drawn.\n" +
                        "Commencing part two. Good luck!\n");

                for (int ball = 0; ball < ballsInPartThree; ball++) {
                    //Draw the ball
                    drawnBall = bingo.drawBall(drawPool);

                    //Check if the drawn ball matches any on the playing field, if yes mark it with '0'
                    bingo.replaceMatchesWithX(playingFieldArr, drawnBall);
                }

                System.out.println("Would you like to see the results? (Y/N)");
                userInput = scanner.next();

                if (userInput.equalsIgnoreCase("y")) {
                    //Print current results
                    bingo.printPlayingField(playingFieldArr, gridSize);

                    //Determine if there is a win, let the user know
                    bingo.determineIfWin(3, playingFieldArr, gridSize);
                } else {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            } else {
                System.out.println("See you next time!");
                System.exit(0);
            }
        } else {
            System.out.println("See you next time!");
            System.exit(0);
        }
        scanner.close();
    }
}
