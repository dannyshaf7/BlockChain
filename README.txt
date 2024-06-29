ARRAYLIST BLOCKCHAIN: 
Arraylist Blockchain is a Java program designed to build a blockchain that can include multiple blocks. Each block contains a list of transactions, a previous hash (the digital signature of the previous block), and the hash of the current block based on the list of transactions and the previous hash. This ensures the integrity of the blockchain, as any modification in a previous block will result in changes to the subsequent blocks' digital signatures.

FEATURES: 
Block Creation: Handles the creation and access of blocks.
Hash Generation: Uses SHA-256 to create a message digest and generate hashes based on transactions and the previous block's hash.
Blockchain Integrity: Ensures that any change in a previous block affects the subsequent blocks, maintaining the integrity of the blockchain.

CLASSES:
Main: Manages menus and basic user interface operations.
Block: Handles block creation and access.
HashGen: Creates message digest using SHA-256 hash; Generates a hash using the previous block's hash and the block's transactions.

LIBRARIES USED:
java.math.BigInteger: For mathematical operations.
java.util.ArrayList: For handling lists of transactions.
java.util.Scanner: For user input.
java.security.MessageDigest: For generating SHA-256 hashes.
java.security.NoSuchAlgorithmException: For handling exceptions related to hashing algorithms.

INSTALLATION:
Ensure you have Java installed on your system. You can download it from 
  <https://www.oracle.com/java/technologies/downloads/#java11>
Clone this repository to your local machine using:
  $ git clone https://github.com/yourusername/arraylist-blockchain.git
Navigate to the project directory:
  $ cd arraylist-blockchain
Compile the Java files:
  $ javac Main.java Block.java HashGen.java

RUN THE PROGRAM:
  $ java Main
  -Follow the on-screen instructions to interact with the blockchain:
  -Create new blocks
  -Add transactions
  -Edit existing blocks
  -Display blockchain

EXAMPLE
Here's a simple example of how the program works:
  Start the program:
  Select options from the menu:
    1. Add a Block
    2. Display a Block
    3. Edit a Block
    4. Display the Whole Blockchain
    9. Quit
  Selecting (1) displays submenu 1:
    1. Submit Block (adds current block to blockchain)
    2. Refresh (allows entry of new transactions and recalculates hash)
       -on complete loops back to submenu 1 to allow submission or further editing
    3. Return to Main Menu
  Selecting (2) requests input from user:
    -user must enter the block number to display
  Selecting (3) requests input from user:
    -user must enter the block number to edit
    -only transactions can be edited
    -changing a block will calculate its hash and all following blocks in blockchain
    -once transaction entry is completed with "done":
      1. Submit Block (updates the edited block in the blockchain and updates hashes
      2. Abandon Changes (cancels the update and returns to main menu
  Selecting (4) displays the entire blockchain
  Selecting (9) quits the program
  
ACKNOWLEDGEMENTS:
Thanks to the authors of the libraries used in this project.

AUTHOR:
Daniel Shafer - June 2024
