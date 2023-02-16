public class BingoCard {
    private int[][] card;
    private boolean[][] taken;
    public static final int[] MY_WINNER = {1, 2, 3, 4, 7, 16, 17, 20, 23, 29, 31, 32, 33, 38, 46, 47, 48, 49, 52, 61, 62, 63, 68, 73};

    public BingoCard(int[] data) // constructor
    {
        int dataIndex = 0;
        card = new int[5][5];	//Initialize the arrays
        taken = new boolean[5][5];

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(i==2 && j==2)	//Index of Free square
                {
                    taken[i][j] = true;
                    card[i][j] = -1;	//Placeholder for Free square
                    continue;	//Skip to next square
                }

                card[i][j] = data[dataIndex];
                taken[i][j] = false;
                dataIndex++;	//Gets next number (free square is ignored)
            }
        }
    }

    private Boolean checkDuplicate(int number, int iIndex, int jIndex) {
        for(int j=0; j<jIndex; j++)	//Checks the previous squares in the column
        {
            if(card[iIndex][j] == number)	//Checks if any previous numbers are same as current
                return true;	//Returns false if there is a duplicate
        }
        return false;	//If no duplicates
    }

    public boolean isValid()
    {
        for(int i=0; i<5; i++)	//Visits each square
        {
            for(int j=0; j<5; j++)
            {
                if(checkDuplicate(card[i][j], i, j))	//Checks if the number has been visited
                    return false;	//Returns false if there is a duplicate
            }
        }

        for(int i=0; i<5; i++)	//Checks each column
        {
            for(int j=0; j<5; j++)	//Checks the number in the column is valid
            {
                if (i == 2 && j == 2)	//Skips Free square
                    continue;

                if(!(card[i][j] >= (1 + i*15) && card[i][j] <= (15 + i*15)))	//Check if number is not valid
                    return false;
            }
        }
        return true;
    }

    public void drawn(int number)
    {
        for(int i=0; i<card.length; i++)
        {
            for(int j=0; j<card.length; j++)
            {
                if(card[i][j] == number)	//Checks if the number in the square is same as drawn
                    taken[i][j] = true;
            }
        }
    }

    public void drawn(int[] numbers)
    {
        for(int i=0; i<card.length; i++)
        {
            for(int j=0; j<card.length; j++)
            {
                for(int z = 0; z < numbers.length; z++)	//Checks all numbers in the drawn array
                {
                    if(card[i][j] == numbers[z])	//Checks if the number in square is drawn number
                        taken[i][j] = true;
                }
            }
        }
    }


    public void reset()
    {
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                taken[i][j] = false;	//Sets all squares as false

                if(i==2 && j==2)	//Sets free square as true
                    taken[i][j] = true;
            }
        }
    }

    public boolean isAWinner()
    {
        int takenNums=0;
        for(int i=0; i<5; i++)
        {
            if(taken[0][i])
                takenNums++;
        }

        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[1][i])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[2][i])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[3][i])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[4][i])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        //

        for(int i=0; i<5; i++)
        {
            if(taken[i][0])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][1])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][2])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][3])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][4])
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        //

        for(int i=0; i<card.length; i++)
        {
            if(taken[i][i])    // checking left diagonal
                takenNums++;
        }
        if(takenNums==5)
            return true;

        takenNums=0;

        for(int i=0; i<card.length; i++)
        {
            if(taken[i][4 - i])    // checking right diagonal
                takenNums++;
        }
        if(takenNums==5)
            return true;

        return false;
    }

    public int minToWin() {
        int takenNums=0;

        int maxTakenNums = 0;	//Stores the maximum number of taken numbers
        for(int i=0; i<5; i++)
        {
            if(taken[0][i])
                takenNums++;
        }

        if(takenNums > maxTakenNums)	//Store maximum taken numbers
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[1][i])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[2][i])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[3][i])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[4][i])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        //

        for(int i=0; i<5; i++)
        {
            if(taken[i][0])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][1])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][2])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][3])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<5; i++)
        {
            if(taken[i][4])
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        //

        for(int i=0; i<card.length; i++)
        {
            if(taken[i][i])    // checking left diagonal
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        takenNums=0;

        for(int i=0; i<card.length; i++)
        {
            if(taken[i][4 - i])    // checking right diagonal
                takenNums++;
        }
        if(takenNums > maxTakenNums)
            maxTakenNums = takenNums;

        return 5 - maxTakenNums;	//Gets remaining number of cards needed to win
    }

    public String toString()
    {
        String result = " B    I    N    G    O   \n";

        for(int j=0; j<card.length; j++)
        {
            for(int i=0; i<card.length; i++)
            {
                if(taken[i][j])	//Printing taken
                {
                    if(i==2 && j==2)	//Print Free
                        result += "[FR] ";
                    else {
                        if(card[i][j]/10 > 0)	//Print two-digit number
                            result += "[" + card[i][j] + "] ";
                        else	//Print one-digit number
                            result += "[ " + card[i][j] + "] ";
                    }
                } else {	//Printing non-taken
                    if(card[i][j]/10 > 0)	//Print two-digit number
                        result += " " + card[i][j] + "  ";
                    else	//Print one-digit number
                        result += "  " + card[i][j] + "  ";
                }
            }
            result +="\n";
        }
        return result;
    }
}