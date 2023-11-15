package rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Encrypt {
    public static void main(String[] args) {
        String message = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("rsa/text/plaintext.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                message += line;
            }
        } catch (FileNotFoundException err) {

        } catch (IOException err) {

        }

        BigInteger n = new BigInteger("1");
        BigInteger e = new BigInteger("1");

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("rsa/receiversPublicKey.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("[n]")) {
                    n = new BigInteger(reader.readLine());
                } else if (line.contains("[e]")) {
                    e = new BigInteger(reader.readLine());
                }

            }

            if (n.equals(BigInteger.ONE) || e.equals(BigInteger.ONE)) {
                System.out.println("ERROR: keys were not properly formatted or read-in");
                System.exit(1);
            }
            
        } catch (FileNotFoundException err) {
            // sout
        } catch (IOException err) {
            // sout
        }

        BigInteger plaintext = new BigInteger(message.getBytes());

        BigInteger ciphertext = plaintext.pow(e.intValue()).mod(n);

        // Write ciphertext
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rsa/text/ciphertext.txt", false))) {

            writer.write(ciphertext.toString());

        } catch (IOException err) {
            System.out.println("\n\n--------ERROR--------\n");
        }
    }

}
