package com.bingo2;

import java.util.Random;

class Bingo2 {
    static int winCounterPartOne;
    static int winCounterPartTwo;
    static int winCounterPartThree;
    static int cycleCount;

    void populatePlayingField(int[][] arrIn, int range, int gridSize) {
        /*Determine numbers to play with in accordance to the rules:
        First column contains a range of 1 - 15, second 16 - 30, third 31 - 45, fourth 46 - 60, fifth 61 - 75.
         */
        int[] usedNumbers = new int[25];
        Random random = new Random();
        int randomValue;
        boolean numberIsUsed;
        int max;
        int min;
        int counter = 0;
        Bingo2 bingo = new Bingo2();

        //For every row and column intersection determine a random value within a certain range that is not in use
        for (int i = 0; i < arrIn[0].length; i++) {
            for (int n = 0; n < arrIn[1].length; n++) {
                do {
                    min = ((n + 1) * (range / gridSize)) - ((range / gridSize) - 1);
                    max = (n + 1) * (range / gridSize);
                    randomValue = random.nextInt((max - min) + 1) + min;
                    numberIsUsed = bingo.checkIfIntIsInArray(randomValue, usedNumbers);
                } while (numberIsUsed);

                arrIn[i][n] = randomValue;
                usedNumbers[counter] = randomValue;
                counter++;
            }
        }
        cycleCount++;
    }

    boolean checkIfIntIsInArray(int intToSearch, int[] arrToSearchFrom) {
        for (int item: arrToSearchFrom) {
            if (item == intToSearch) {
                return true;
            }
        }
        return false;
    }

    void printPlayingField (int[][] arrIn, int gridSize) {
        System.out.println(" B  I  N  G  O");
        for (int i = 0; i < arrIn[0].length; i++) {
            for (int n = 0; n < arrIn[1].length; n++) {
                if (n < gridSize - 1) {
                    if (String.valueOf(arrIn[i][n]).length() == 1) {
                        //Values that are of length 1 will get a space in front of them to keep alignment
                        System.out.print(" " + arrIn[i][n] + " ");
                    } else {
                        System.out.print(arrIn[i][n] + " ");
                    }
                } else {
                    //Last column will get a new line
                    if (String.valueOf(arrIn[i][n]).length() == 1) {
                        //Values that are of length 1 will get a space in front of them to keep alignment
                        System.out.println(" " + arrIn[i][n] + " ");
                    } else {
                        System.out.println(arrIn[i][n]);
                    }
                }
            }
        }
    }

    int drawBall(int[] drawPool) {
        Bingo2 bingo = new Bingo2();
        boolean newBall;
        int newBallValue;

        Random random = new Random();
        int randomValue;

        while (true) {
            //Get a random value that represents an index in the drawPool array
            randomValue = random.nextInt(drawPool.length);

            //Check if it's 0, if not make it 0 before returning the corresponding value from that index in that array
            if (drawPool[randomValue] != 0) {
                newBallValue = drawPool[randomValue];
                drawPool[randomValue] = 0;
                return newBallValue;
            }
        }
    }

    void replaceMatchesWithX (int[][] playingFieldArr, int potentialMatch) {
        for (int i = 0; i < playingFieldArr[0].length; i++) {
            for (int n = 0; n < playingFieldArr[0].length; n++) {
                if (potentialMatch == playingFieldArr[i][n]) {
                    playingFieldArr[i][n] = 0;
                }
            }
        }
    }

    void determineIfWin(int part, int[][] playingFieldArr, int gridSize) {
        int counter = 0;
        if (part == 1) {
            if (playingFieldArr[0][0] == 0 && playingFieldArr[0][4] == 0 && playingFieldArr[4][4] == 0 && playingFieldArr[4][0] == 0 ) {
                //System.out.println("PART ONE: YOU WON!\n");
                winCounterPartOne++;
            } else {
                if (playingFieldArr[0][0] != 0) {
                    counter++;
                }
                if (playingFieldArr[0][4] != 0) {
                    counter++;
                }
                if (playingFieldArr[4][4] != 0) {
                    counter++;
                }
                if (playingFieldArr[4][0] != 0) {
                    counter++;
                }
                //System.out.println("PART ONE: YOU LOST!\n"+"("+counter+" remaining)\n");
            }
        } else if (part == 2) {
            boolean win = true;

            for (int i = 0; i < gridSize; i++) {
                if (playingFieldArr[i][i] != 0) {
                    win = false;
                    break;
                }
            }

            if (win) {
                if (playingFieldArr[0][4] == 0 && playingFieldArr[1][3] == 0 && playingFieldArr[3][1] == 0 && playingFieldArr[4][0] == 0) {
                    //System.out.println("PART TWO: YOU WON!\n");
                    winCounterPartTwo++;
                } else {
                    //System.out.println("PART TWO: YOU LOST!\n");
                }
            } else {
                for (int i = 0; i < gridSize; i++) {
                    if (playingFieldArr[i][i] != 0) {
                        if (playingFieldArr[i][i] != 0) {
                            counter++;
                        }
                    }
                }
                if (playingFieldArr[0][4] != 0) {
                    counter++;
                }
                if (playingFieldArr[1][3] != 0) {
                    counter++;
                }
                if (playingFieldArr[3][1] != 0) {
                    counter++;
                }
                if (playingFieldArr[4][0] != 0) {
                    counter++;
                }
                //System.out.println("PART TWO: YOU LOST!\n"+"("+counter+" remaining)\n");
            }
        } else {
            boolean win = true;

            for (int i = 0; i < playingFieldArr.length; i++) {
                for (int n = 0; n < playingFieldArr.length; n++) {
                    if (playingFieldArr[i][n] != 0) {
                        win = false;
                        counter++;
                    }
                }
            }

            if (win) {
                //System.out.println("PART THREE: YOU WON!");
                winCounterPartThree++;
            } else {
                //System.out.println("PART THREE: YOU LOST!\n"+"("+counter+" remaining)");
            }
        }
    }
    void printWinStats () {
        System.out.println("Win counts in PART ONE: "+winCounterPartOne);
        System.out.println("Win counts in PART TWO: "+winCounterPartTwo);
        System.out.println("Win counts in PART THREE: "+winCounterPartThree);
        System.out.println("Total cycles: "+cycleCount);
    }
}
