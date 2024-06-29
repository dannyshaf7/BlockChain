import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/*
   Daniel Shafer - Summer 2024
   Programming Project - Build a Blockchain
   CPSC 3555 - Intro to Blockchain
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
            if (userInput.hasNextInt()) { menuChoice = userInput.nextInt(); }
            switch (menuChoice) {
                case 1:
                    ArrayList<String> transArray = new ArrayList<>();
                    int count = 0;
                    Block nextBlock = null;
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
                        for (int i = 0; i < transArray.size(); i++) { transactions[i] = transArray.get(i); }
                        if (blockChain.size() > 0) {
                            int blockIndex = blockChain.size();
                            BigInteger previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                            nextBlock = new Block(previousHash, transactions);
                        } else {
                            BigInteger previousHash = BigInteger.valueOf(0);
                            nextBlock = new Block(previousHash, transactions);
                        }
                        System.out.println(nextBlock.toString());
                    }
                    userInput = new Scanner(System.in);
                    boolean exit = false;
                    while (!exit) {
                        System.out.println(subMenuOne);
                        if (userInput.hasNextInt()) {
                            submenuChoice = userInput.nextInt();
                        }
                        switch (submenuChoice) {
                            case 1:
                                System.out.println("1 selected. Submit " +
                                        "Block (Add Current Block to Blockchain) \n");
                                if (nextBlock != null) {
                                    blockChain.add(nextBlock);
                                }
                                exit = true;
                                break;
                            case 2:
                                System.out.println("2 selected. Edit, Refresh, and Recalculate Hash. \n");
                                transArray = new ArrayList<>();
                                count = 0;
                                System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                                userInput = new Scanner(System.in);
                                newTrans = userInput.nextLine();
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
                                        nextBlock = new Block(previousHash, transactions);
                                    } else {
                                        BigInteger previousHash = BigInteger.valueOf(0);
                                        nextBlock = new Block(previousHash, transactions);
                                    }
                                    System.out.println(nextBlock.toString());
                                }
                                break;
                            case 3:
                                exit = true;
                                break;
                            default:
                                System.out.println("Input Error. Please try again.");
                                break;
                        }
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
                            ArrayList<String> editTransArray = new ArrayList<>();
                            int editCount = 0;
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
                                editTransArray.add(editTrans);
                                editCount += 1;
                                System.out.println("Please enter the name of the payer (or \"done\" to end): \n");
                                userInput = new Scanner(System.in);
                                editTrans = userInput.nextLine();
                            }
                            if (editTransArray.size() > 0) {
                                System.out.println("The new transactions you entered are: \n" + editTransArray + "\n" +
                                        "Please enter 1 to submit your changes or 2 to abandon changes and return to " +
                                        "Main.\n!*Please note than this will change this block hash and all " +
                                        "following hashes*!\n");
                                userInput = new Scanner(System.in);
                                if (userInput.hasNextInt()) {
                                    int submenu2Choice = userInput.nextInt();
                                    switch (submenu2Choice) {
                                        case 1:
                                            String[] transactions = new String[editCount];
                                            for (int i = 0; i < editTransArray.size(); i++) {
                                                transactions[i] = editTransArray.get(i);
                                            }
                                            int blockIndex = submenuChoice;
                                            if (submenuChoice > 0) {
                                                BigInteger previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                                Block blockEdit = new Block(previousHash, transactions);
                                                blockChain.set(blockIndex, blockEdit);
                                                System.out.println(blockChain);
                                                blockIndex += 1;
                                                int chainSize = blockChain.size();
                                                System.out.println(chainSize);
                                                while (blockIndex < chainSize) {
                                                    previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                                    transactions = blockChain.get(blockIndex - 1).getTransaction();
                                                    blockEdit = new Block(previousHash, transactions);
                                                    blockChain.set(blockIndex, blockEdit);
                                                    blockIndex += 1;
                                                }
                                            } else {
                                                BigInteger previousHash = BigInteger.valueOf(0);
                                                Block blockEdit = new Block(previousHash, transactions);
                                                blockChain.set(blockIndex, blockEdit);
                                                System.out.println(blockChain);
                                                blockIndex += 1;
                                                int chainSize = blockChain.size();
                                                System.out.println(chainSize);
                                                while (blockIndex < chainSize) {
                                                    previousHash = blockChain.get(blockIndex - 1).getBlockHash();
                                                    transactions = blockChain.get(blockIndex - 1).getTransaction();
                                                    blockEdit = new Block(previousHash, transactions);
                                                    blockChain.set(blockIndex, blockEdit);
                                                    blockIndex += 1;
                                                }
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Changes cancelled. Returning to Main Menu.");
                                            break;
                                        default:
                                            System.out.println("Input Error. Please try again.");
                                            break;
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("The block does not exist in this blockchain. Returning to Main Menu. ");
                        }
                    } else {
                        System.out.println("Input Error: not a valid number. Returning to Main Menu. \n");
                    }
                    break;
                case 4:
                    for (Block aBlock : blockChain) { System.out.println(aBlock.toString()); }
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
