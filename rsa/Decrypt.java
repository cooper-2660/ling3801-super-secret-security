package rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Decrypt {
    public static void main(String[] args) {
        String message = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("rsa/text/ciphertext.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                message += line;
            }
        } catch (FileNotFoundException err) {

        } catch (IOException err) {

        }

        BigInteger ciphertext = new BigInteger(message);

        BigInteger d = new BigInteger("1");
        BigInteger n = new BigInteger("1");

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("rsa/private/key.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("[d]")) {
                    d = new BigInteger(reader.readLine());
                } else if (line.contains("[n]")) {
                    n = new BigInteger(reader.readLine());
                }

            }
        } catch (FileNotFoundException err) {
            // sout
        } catch (IOException err) {
            // sout
        }

        BigInteger cipherArray = ciphertext.modPow(d, n);

        String plaintext = "";

        plaintext = new String(cipherArray.toByteArray());

        // Write plaintext
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rsa/text/plaintext.txt", false))) {

            writer.write(plaintext);

        } catch (IOException err) {
            System.out.println("\n\n--------ERROR--------\n");
        }
    }
}
