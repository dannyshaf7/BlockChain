import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGen {

    public BigInteger createBlockHash(BigInteger previousHash, String[] transactions){
        StringBuilder transString = new StringBuilder();
        transString.append(previousHash);
        for (String transaction : transactions) {
            transString.append(transaction);
        }
        byte[] byteArray = transString.toString().getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(byteArray);
            StringBuilder result = new StringBuilder();
            for (byte aByte : hashBytes) {
                result.append(String.format("%02x", aByte));
            }
            BigInteger hexInt = new BigInteger(result.toString(), 16);
            return hexInt;
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown: " + e);
            return null;
        }
        catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
            return null;
        }
    }
}
