import java.util.*;

public class Vignere {
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        
        // Get plaintext input
        System.out.println("Enter plaintext: "); 
        StringBuilder plainText = new StringBuilder(sc.nextLine()); 
        
        // Get key input
        System.out.println("Enter key: "); 
        StringBuilder key = new StringBuilder(sc.nextLine()); 
        
        // Standardize both plaintext and key
        StringBuilder standardText = standard(plainText); 
        StringBuilder standardKey = standard(key); 
        
        int lengthST = standardText.length(); 
        int lengthKey = key.length(); 
        
        StringBuilder newKey = new StringBuilder(); 
        
        // Adjust the key length to match the plaintext length
        if (lengthKey < lengthST) { 
            int factor = lengthST / lengthKey; 
            for (int i = 0; i < factor; i++) { 
                newKey.append(standardKey); 
            } 
            int charLen = lengthST % lengthKey; 
            String strip = standardKey.substring(0, charLen); 
            newKey.append(strip); 
        } else {
            newKey = standardKey;
        }
        
        // Encrypt the plaintext
        StringBuilder encryptedText = encrypt(standardText, newKey); 
        System.out.println("The encrypted text is: " + encryptedText); 
        
        // Decrypt the encrypted text
        StringBuilder decryptedText = decrypt(encryptedText, newKey); 
        System.out.println("The decrypted text is: " + decryptedText); 
        
        sc.close(); 
    }
    
    // Method to standardize text (remove non-alphabet characters and convert to uppercase)
    static StringBuilder standard(StringBuilder text) { 
        String plain = text.toString(); 
        plain = plain.replaceAll("[^a-zA-Z]", ""); 
        plain = plain.toUpperCase(); 
        return new StringBuilder(plain); 
    } 
    
    // Method to encrypt the plaintext using the Vigenère cipher
    static StringBuilder encrypt(StringBuilder plainText, StringBuilder key) { 
        StringBuilder encryptedText = new StringBuilder(); 
        for (int i = 0; i < plainText.length(); i++) { 
            int charText = plainText.charAt(i) - 'A'; 
            int charKey = key.charAt(i) - 'A'; 
            int encrypt = (charText + charKey) % 26; 
            char encryptText = (char) (encrypt + 'A'); 
            encryptedText.append(encryptText); 
        } 
        return encryptedText; 
    } 
    
    // Method to decrypt the ciphertext using the Vigenère cipher
    static StringBuilder decrypt(StringBuilder encryptedText, StringBuilder key) { 
        StringBuilder decryptedText = new StringBuilder(); 
        for (int i = 0; i < encryptedText.length(); i++) { 
            int charText = encryptedText.charAt(i) - 'A'; 
            int charKey = key.charAt(i) - 'A'; 
            int decrypt = (charText - charKey) % 26; 
            if (decrypt < 0) { 
                decrypt += 26; 
            } 
            char decryptText = (char) (decrypt + 'A'); 
            decryptedText.append(decryptText); 
        } 
        return decryptedText; 
    } 
}
