import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    // Function to compute gcd of two BigIntegers
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = a.mod(b);
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input prime numbers p and q
        System.out.print("Enter first prime number (p): ");
        BigInteger p = scanner.nextBigInteger();

        System.out.print("Enter second prime number (q): ");
        BigInteger q = scanner.nextBigInteger();

        // Calculate n = p * q and phi = (p-1)(q-1)
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Select a standard public exponent e (common choice is 65537)
        BigInteger e = BigInteger.valueOf(65537);

        // Ensure e and phi are coprime
        if (!gcd(e, phi).equals(BigInteger.ONE)) {
            System.out.println("e and phi(n) are not coprime. Exiting...");
            scanner.close();
            return;
        }

        // Compute the modular inverse of e, i.e., d
        BigInteger d = e.modInverse(phi);

        // Input the message to be encrypted
        System.out.print("Enter message to be encrypted (as a number): ");
        BigInteger msg = scanner.nextBigInteger();

        // Encrypt the message: c = msg^e mod n
        BigInteger c = msg.modPow(e, n);

        // Decrypt the message: m = c^d mod n
        BigInteger m = c.modPow(d, n);

        // Output the results
        System.out.println("Encrypted data = " + c);
        System.out.println("Decrypted Message = " + m);

        scanner.close();
    }
}
