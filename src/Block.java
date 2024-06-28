import java.math.BigInteger;

public class Block {
    //Data Members
    private BigInteger previousHash;
    private String[] transactions;
    private BigInteger blockHash;

    //Constructors
    public Block (BigInteger prevHash, String[] trans) {
        this.previousHash = prevHash;
        this.transactions = trans;

        HashGen newHash = new HashGen();
        this.blockHash = newHash.createBlockHash(previousHash, transactions);
    }

    //Methods
    public BigInteger getPreviousHash(){
        return previousHash;
    }
    public String[] getTransaction(){
        return transactions;
    }
    public BigInteger getBlockHash(){
        return blockHash;
    }
    public String toString(){
        String transString = "";
        for (int i = 0; i < transactions.length; i++) {
            transString += transactions[i];
        }
        String blockPrint = "Previous Hash: " + previousHash.toString(16) + "\n" +
                "Current Hash: " + blockHash.toString(16) + "\n" +
                "Transactions: " + transString + "\n";
        return blockPrint;
    }
}
