package rsa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class GenerateKeyPair {
    private static BigInteger lowestCommonMultiple(BigInteger p_minus_one, BigInteger q_minus_one) {
        final BigInteger pTimesQ = p_minus_one.multiply(q_minus_one);

        final BigInteger greatestCommonDivisor = p_minus_one.gcd(q_minus_one);

        return pTimesQ.divide(greatestCommonDivisor);
    }

    private static BigInteger getCoPrimeToE(Random rnd, int bitLength, BigInteger e) {
        BigInteger largePrimeNumber;
        do {
            largePrimeNumber = BigInteger.probablePrime(bitLength, rnd);

        } while (largePrimeNumber.mod(e).equals(BigInteger.ONE));

        return largePrimeNumber;
    }

    private static void writePublicKey(BigInteger n, BigInteger e) {
        // Write key
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rsa/public/key.txt", false))) {

            writer.write("Public Key [n]: \n");
            writer.write(n.toString());
            writer.write("\n");

            writer.write("Public Key [e]: \n");
            writer.write(e.toString());
            writer.write("\n");

        } catch (IOException err) {
            System.out.println("\n\n--------ERROR--------\n");
        }
    }

    private static void writePrivateKey(BigInteger d, BigInteger n) {
        // Write key
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rsa/private/key.txt", false))) {

            writer.write("Private Key [d]: \n");
            writer.write(d.toString());
            writer.write("\n");

            writer.write("Private Key [n]: \n");
            writer.write(n.toString());
            writer.write("\n");

        } catch (IOException err) {
            System.out.println("\n\n--------ERROR--------\n");
        }
    }

    public static void main(String[] args) {
        final Random rnd = new Random();
        final int bitLength = Integer.parseInt(args[0]);

        /*
         * Note: I am using the algorithm and notation found on Wikipedia. Links:
         * 
         * https://en.wikipedia.org/wiki/RSA_(cryptosystem)#:~:text=6%20September%202000
         * .-,Operation,0%20%E2%89%A4%20m%20%3C%20n)%3A
         * 
         * https://crypto.stackexchange.com/questions/13166/method-to-calculating-e-in-
         * rsa
         * 
         * https://web.archive.org/web/20230127011251/http://people.csail.mit.edu/rivest
         * /Rsapaper.pdf
         * 
         */

        // Note: 65537 is a special value for e.
        final BigInteger e = new BigInteger("65537");

        // Find p s.t.
        final BigInteger p = getCoPrimeToE(rnd, bitLength, e);
        final BigInteger p_minus_one = p.subtract(BigInteger.ONE);

        // Find q s.t.
        final BigInteger q = getCoPrimeToE(rnd, bitLength, e);
        final BigInteger q_minus_one = q.subtract(BigInteger.ONE);

        final BigInteger n = p.multiply(q);

        // Find Lamda(n) s.t.
        final BigInteger lamda_n = lowestCommonMultiple(p_minus_one, q_minus_one);

        // Find d s.t.
        final BigInteger d = e.modInverse(lamda_n);

        writePublicKey(n, e);
        writePrivateKey(d, n);
    }

}
