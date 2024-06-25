import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/* Questions for professor:
    1. The sample output select 1.2 submenu instructions says to recalculate previous hash. If the user is only editing
       in this submenu the block they are adding to the blockchain, the previous hash would not change.
       a. "If transactions are edited or added, it needs refresh first before submit it." Does this mean that if the
          user selects menu option 3 and edits the transaction that it's not supposed to be added until they go back
          to submenu 1.2 to refresh and then 1.1 to submit?
 * To-do list:
    1. Change to accept transactions until "done" is entered.
    2. Add edit block functionality:
        a. When any block is changed, its hash changes and so each following hashes changes (all blocks should update).
        b. Must verify and show editing a transaction in a block will break the consistency with the "previousHash" in
           the block after the edited one.
    3. Change display block error to the following:
        a. "The block does not exist in this Blockchain."
    4. Change the add a block to display the proposed block and ask for confirmation?
        a. If 1.2 is selected recalculate previous hash, current hash.

*/

public class Main {
    public static void main(String[] args) {

        String menu = "This is a Blockchain! Please select from the following: \n" +
                "1. Add a Block \n" +
                "2. Display a Block \n" +
                "3. Edit a Block \n" +
                "4. Display the Whole Blockchain \n" +
                "9. (Quit) \n";

        String subMenuOne = "Blockchain Submenu: \n" +
                "1. Submit Block (Add Current Block to Blockchain) \n" +
                "2. Refresh (Recalculate Previous Hash and Previous Hash) \n" +
                "3. Return to Main Menu \n";

        String subMenuTwo = "Display Block Submenu: \n" +
                "Please input the block number you wish to see. \n";

        String subMenuThree = "Edit Block Submenu: \n" +
                "Please input the block number you wish to edit. \n";

        ArrayList<Block> blockChain = new ArrayList<>();
        boolean continueFlag = true;
        while (continueFlag) {
            int menuChoice = -1;
            int submenuChoice = -1;
            System.out.println(menu);
            Scanner userInput = new Scanner(System.in);
            if (userInput.hasNextInt()) {
                menuChoice = userInput.nextInt();
            }
            switch (menuChoice) {
                case 1:
                    System.out.println(subMenuOne);
                    userInput = new Scanner(System.in);
                    if (userInput.hasNextInt()) {
                        submenuChoice = userInput.nextInt();
                    }
                    switch (submenuChoice) {
                        case 1:
                            System.out.println("1 selected. Submit " +
                                    "Block (Add Current Block to Blockchain) \n");
                            System.out.println("Please enter the number of transactions to add: \n");
                            userInput = new Scanner(System.in);
                            while (!userInput.hasNextInt()) {
                                System.out.println("Input error. Please try again.\n +" +
                                        "Please enter the number of transactions to add: \n");
                                userInput = new Scanner(System.in);
                            }
                            int numTrans = userInput.nextInt();
                            System.out.println("number of transactions: " + numTrans);
                            String[] transactions = new String[numTrans];
                            for (int i = 0; i < transactions.length; i++) {
                                System.out.println("Please enter the name of the payer: \n");
                                userInput = new Scanner(System.in);
                                String newTrans = userInput.nextLine() + " pays ";
                                System.out.println("Please enter the number of bitcoins to pay: \n");
                                userInput = new Scanner(System.in);
                                while (!userInput.hasNextInt()) {
                                    System.out.println("Input error. Please try again.\n +" +
                                            "Please enter the number of bitcoins to pay: \n");
                                    userInput = new Scanner(System.in);
                                }
                                newTrans += userInput.nextInt() + " BTC to ";
                                System.out.println("Please enter the name of the payee: \n");
                                userInput = new Scanner(System.in);
                                newTrans += userInput.nextLine() + "; ";
                                transactions[i] = newTrans;
                            }
                            if (blockChain.size() > 0) {
                                int blockIndex = blockChain.size();
                                BigInteger previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                Block nextBlock = new Block(previousHash, transactions);
                                blockChain.add(nextBlock);
                            } else {
                                BigInteger previousHash = BigInteger.valueOf(0);
                                Block genesisBlock = new Block(previousHash, transactions);
                                blockChain.add(genesisBlock);
                                genesisBlock.getBlockHash();
                            }
                            break;
                        case 2:
                            System.out.println("2 selected. Refresh " +
                                    "(Recalculate Previous Hash and Previous \n" +
                                    "Hash) is under construction. Returning " +
                                    "to Main Menu.");
                            break;
                        case 3:
                            System.out.println("3 selected. Returning " +
                                    "to Main Menu. \n");
                            break;
                        default:
                            System.out.println("Input Error. Please try again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println(subMenuTwo);
                    userInput = new Scanner(System.in);
                    if (userInput.hasNextInt()) {
                        submenuChoice = userInput.nextInt() - 1;
                        if (submenuChoice >= 0 && submenuChoice < blockChain.size()) {
                            System.out.println(blockChain.get(submenuChoice).toString());
                        }
                        else {
                            System.out.println("Input Error: not a valid block number. Returning to Main Menu. ");
                        }
                    } else {
                        System.out.println("Input Error: not a valid number. Returning to " +
                                "Main Menu. \n");
                    }
                    break;
                case 3:
                    System.out.println(subMenuThree);
                    userInput = new Scanner(System.in);
                    if (userInput.hasNextInt()) {
                        submenuChoice = userInput.nextInt();
                        System.out.println(submenuChoice + " selected. " +
                                "Edit Block is under construction. \n");
                    } else {
                        System.out.println("Input Error. Returning to " +
                                "Main Menu. \n");
                    }
                case 4:
                    for (Block aBlock : blockChain) {
                        System.out.println(aBlock.toString());
                    }
                    break;
                case 9:
                    System.out.println("9 selected. Goodbye!");
                    continueFlag = false;
                    break;
                default:
                    System.out.println("Input Error. Please try again.");
                    break;
            }
        }
    }
}
