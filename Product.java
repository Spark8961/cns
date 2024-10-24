import java.util.Scanner;

public class Product {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get input string from the user
        System.out.println("Enter a string");
        StringBuilder plainText = new StringBuilder(sc.nextLine());
        StringBuilder stdPlain = standard(plainText);

        int matrixSize = 0;

        // Determine matrix size based on the length of the standardized plaintext
        if (stdPlain.length() <= 9) {
            matrixSize = 3;
        } else if (stdPlain.length() <= 16) {
            matrixSize = 4;
        } else if (stdPlain.length() <= 25) {
            matrixSize = 5;
        } else {
            System.out.println("Enter a smaller string");
        }

        // Caesar cipher encryption
        StringBuilder ceaserText = ceaserEncrypt(stdPlain);

        // Transposition cipher encryption
        StringBuilder[] encryptedText = transpositionEncrypt(ceaserText, matrixSize);
        System.out.println("Encrypted Text: " + encryptedText[0]);

        // Transposition cipher decryption
        StringBuilder[] decryptTransposition = transpositionDecrypt(encryptedText[1], matrixSize);

        // Caesar cipher decryption
        StringBuilder decryptText = ceaserDecrypt(decryptTransposition[0]);
        System.out.println("Decrypted Text: " + decryptText);

        sc.close();
    }

    // Standardize input (remove non-alphabet characters and convert to uppercase)
    static StringBuilder standard(StringBuilder plaintext) {
        String plain = plaintext.toString();
        plain = plain.replaceAll("[^a-zA-Z]", ""); // Remove non-alphabet characters
        plain = plain.toUpperCase();               // Convert to uppercase
        return new StringBuilder(plain);
    }

    // Caesar Cipher Encryption (shift by 3)
    static StringBuilder ceaserEncrypt(StringBuilder plainText) {
        StringBuilder encryptCeaser = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int index = ((plainText.charAt(i) + 3) - 'A') % 26; // Shift by 3 positions
            char encyrpt = (char) (index + 'A');
            encryptCeaser.append(encyrpt);
        }
        return encryptCeaser;
    }

    // Transposition Cipher Encryption
    static StringBuilder[] transpositionEncrypt(StringBuilder plainText, int matrixSize) {
        StringBuilder[] encryptTransposition = new StringBuilder[2];
        char[] array = new char[25];

        // Convert plainText to array and fill remaining with '*'
        for (int i = 0; i < plainText.length(); i++) {
            array[i] = plainText.charAt(i);
        }

        char[][] matrix = new char[matrixSize][matrixSize];
        int count = 0;

        // Fill matrix with text and pad with '*'
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (array[count] != '\0') {
                    matrix[i][j] = array[count];
                    count++;
                } else {
                    matrix[i][j] = '*';
                }
            }
        }

        // Print the matrix for debugging
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Column-wise encryption
        encryptTransposition[0] = new StringBuilder("");
        encryptTransposition[1] = new StringBuilder("");
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[j][i] != '*') {
                    encryptTransposition[0].append(matrix[j][i]);
                }
                encryptTransposition[1].append(matrix[j][i]);
            }
        }

        return encryptTransposition;
    }

    // Caesar Cipher Decryption (shift by 3)
    static StringBuilder ceaserDecrypt(StringBuilder plainText) {
        StringBuilder decryptCeaser = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int index = ((plainText.charAt(i) - 3) - 'A') % 26;
            if (index < 0) {
                index += 26;
            }
            char decrypt = (char) (index + 'A');
            decryptCeaser.append(decrypt);
        }
        return decryptCeaser;
    }

    // Transposition Cipher Decryption
    static StringBuilder[] transpositionDecrypt(StringBuilder plainText, int matrixSize) {
        StringBuilder[] decryptTransposition = new StringBuilder[2];
        char[] array = new char[25];

        // Convert plainText to array
        for (int i = 0; i < plainText.length(); i++) {
            array[i] = plainText.charAt(i);
        }

        char[][] matrix = new char[matrixSize][matrixSize];
        int count = 0;

        // Fill matrix with text from array
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = array[count];
                count++;
            }
        }

        // Print the matrix for debugging
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Column-wise decryption
        decryptTransposition[0] = new StringBuilder("");
        decryptTransposition[1] = new StringBuilder("");
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[j][i] != '*') {
                    decryptTransposition[0].append(matrix[j][i]);
                }
                decryptTransposition[1].append(matrix[j][i]);
            }
        }

        return decryptTransposition;
    }
}
