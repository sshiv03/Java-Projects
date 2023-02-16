public class BingoSim {
    private int numCards;
    private boolean[] taken;
    private BingoCard[] cards;

    public BingoSim(int max) {
        cards = new BingoCard[max];
        taken = new boolean[75];	//Stores each drawn card
    }

    public void addCard(BingoCard b) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {	//Check if there is no Card in current Index
                cards[i] = b;	//Add new card to current Index
                break;	//Exit
            }
        }
    }

    public String simulate(int[] sequence) {
        String result = "Simulating ...\n";
        Boolean winFound = false;
        int letter;

        for(int i = 0; i < sequence.length; i++) {
            taken[sequence[i] - 1] = true;	//Marks number as taken (number - 1 = index of that number)
            numCards++;	//Stores drawn cards

            result += (i+1) + ". ";

            letter = sequence[i]/15;	//Divides number by 15 to get letter

            if(sequence[i] % 15 == 0)	//If the number is divisible by 15
                letter -= 1;	//Adjusts letter as they include multiple of 15 at the end (ex. B includes 15)

            if(letter == 0)			//0 - 15
                result += "B-";
            else if(letter == 1) 	//16 - 30
                result += "I-";
            else if(letter == 2)	//31 - 45
                result += "N-";
            else if(letter == 3)	//46 - 60
                result += "G-";
            else if(letter == 4)	//61 - 75
                result += "O-";

            result += sequence[i] + " ";

            int j = 0;
            while(cards[j] != null) {	//Gets all cards
                cards[j].drawn(sequence[i]);	//Draws the next number

                if(cards[j].isAWinner())	//Records if there is a winner
                    winFound = true;

                result += cards[j].minToWin() + " ";	//Adds the new min to win value
                j++;
            }
            result += "\n";

            if(winFound == true)	//Stops drawing numbers if there is a winner
                break;
        }
        return result;
    }

    public String showCardsWithMin(int min) {
        String result = "";

        int i = 0;
        while(cards[i] != null) {	//Check all cards
            if(cards[i].minToWin() == min)	//Checks if the min to Win is same as input
                result += cards[i].toString();
            i++;
        }
        //System.out.println(result);
        return result;
    }

    public String toString() {
        String board = " B    I    N    G    O   \n";
        int temp;
        int winners = 0;

        for(int i = 1; i <= 15; i++) {	//Print ith row
            for(int multi = 0; multi <= 4; multi++) {
                temp = i + 15 * multi;	//Gets the number in each column by adding a multiple

                if(taken[temp - 1]) {	//Printing taken
                    if(temp/10 > 0)	//Print two-digit number
                        board += "[" + temp + "] ";
                    else	//Print one-digit number
                        board += "[ " + temp + "] ";
                } else {	//Printing non-taken
                    if(temp/10 > 0)	//Print two-digit number
                        board += " " + temp + "  ";
                    else	//Print one-digit number
                        board += "  " + temp + "  ";
                }
            }
            board += "\n";
        }

        board += "# Drawn: " + numCards + "\n";
        board += "mins:";

        int cardIndex = 0;
        while(cards[cardIndex] != null) {
            board += cards[cardIndex].minToWin() + " "; //Stores the min to win for each card

            if(cards[cardIndex].isAWinner()) {	//Stores the winners
                winners++;
            }
            cardIndex++;
        }

        board += "\n";
        board += "# Winners: " + winners + " out of " + cardIndex + "\n";

        return board;
    }
}