import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/* Questions for professor:
    1.
    2. Do we include all data (the previous hash and the transactions when calculating the block hash or just the
       transactions)?
 * To-do list:
    1. Add edit block functionality:
        a. When any block is changed, its hash changes and so each following hashes changes (all blocks should update).
        b. Must verify and show editing a transaction in a block will break the consistency with the "previousHash" in
           the block after the edited one.
        c. add to string (cannot be edited) or (for editing)
        ***mostly functional, switch out set for add function on the arraylist
    2. Change the add a block to display the proposed block and ask for confirmation?
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
                "Please input the block number you wish to edit. \n" +
                "Note: only the transactions can be edited. Any changes will also change this block hash and " +
                "hashes following";

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
                            ArrayList<String> transArray = new ArrayList<>();
                            int count = 0;
                            System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                            userInput = new Scanner(System.in);
                            String newTrans = userInput.nextLine();
                            while (!newTrans.equals("done")) {
                                newTrans += " pays ";
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
                                transArray.add(newTrans);
                                count += 1;
                                System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                                userInput = new Scanner(System.in);
                                newTrans = userInput.nextLine();
                            }
                            if (transArray.size() > 0) {
                                String[] transactions = new String[count];
                                for (int i = 0; i < transArray.size(); i++) {
                                    transactions[i] = transArray.get(i);
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
                                }
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
                            System.out.println(blockChain.get(submenuChoice).toString() + "\n");
                        }
                        else {
                            System.out.println("The block does not exist in this blockchain. Returning to Main Menu. ");
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
                        submenuChoice = userInput.nextInt() - 1;
                        if (submenuChoice >= 0 && submenuChoice < blockChain.size()) {
                            System.out.println(blockChain.get(submenuChoice).toString());
                            ArrayList<String> transArray = new ArrayList<>();
                            int count = 0;
                            System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                            userInput = new Scanner(System.in);
                            String editTrans = userInput.nextLine();
                            while (!editTrans.equals("done")) {
                                editTrans += " pays ";
                                System.out.println("Please enter the number of bitcoins to pay: \n");
                                userInput = new Scanner(System.in);
                                while (!userInput.hasNextInt()) {
                                    System.out.println("Input error. Please try again.\n +" +
                                            "Please enter the number of bitcoins to pay: \n");
                                    userInput = new Scanner(System.in);
                                }
                                editTrans += userInput.nextInt() + " BTC to ";
                                System.out.println("Please enter the name of the payee: \n");
                                userInput = new Scanner(System.in);
                                editTrans += userInput.nextLine() + "; ";
                                transArray.add(editTrans);
                                count += 1;
                                System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                                userInput = new Scanner(System.in);
                                editTrans = userInput.nextLine();
                            }
                            if (transArray.size() > 0) {
                                String[] transactions = new String[count];
                                for (int i = 0; i < transArray.size(); i++) {
                                    transactions[i] = transArray.get(i);
                                }
                                // System.out.println(transArray);
                                int blockIndex = submenuChoice;
                                // System.out.println(submenuChoice);
                                if (submenuChoice > 0) {
                                    // System.out.println("looloo");
                                    BigInteger previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                    Block blockEdit = new Block(previousHash, transactions);
                                    // System.out.println(blockIndex);
                                    blockChain.set(blockIndex, blockEdit);
                                    System.out.println(blockChain);
                                    blockIndex += 1;
                                    // System.out.println(blockIndex);
                                    int chainSize = blockChain.size();
                                    System.out.println(chainSize);
                                    // for (int i = blockIndex; i < blockChain.size(); i++) {
                                    while (blockIndex < chainSize) {
                                        // System.out.println(blockIndex);
                                        // System.out.println(blockChain.size());
                                        previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                        transactions = blockChain.get(blockIndex - 1).getTransaction();
                                        blockEdit = new Block(previousHash, transactions);
                                        blockChain.set(blockIndex, blockEdit);
                                        blockIndex += 1;
                                    }
                                } else {
                                    BigInteger previousHash = BigInteger.valueOf(0);
                                    Block blockEdit = new Block(previousHash, transactions);
                                    // System.out.println(blockIndex);
                                    blockChain.set(blockIndex, blockEdit);
                                    System.out.println(blockChain);
                                    blockIndex += 1;
                                    // System.out.println(blockIndex);
                                    int chainSize = blockChain.size();
                                    System.out.println(chainSize);
                                    // for (int i = blockIndex; i < blockChain.size(); i++) {
                                    while (blockIndex < chainSize) {
                                        // System.out.println(blockIndex);
                                        // System.out.println(blockChain.size());
                                        previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                        transactions = blockChain.get(blockIndex - 1).getTransaction();
                                        blockEdit = new Block(previousHash, transactions);
                                        blockChain.set(blockIndex, blockEdit);
                                        blockIndex += 1;
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("The block does not exist in this blockchain. Returning to Main Menu. ");
                        }
                    } else {
                        System.out.println("Input Error: not a valid number. Returning to " +
                                "Main Menu. \n");
                    }
                    break;
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
