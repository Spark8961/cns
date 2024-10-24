def generate_key_table(key):
    key = key.upper().replace('J', 'I')
    key = ''.join(sorted(set(key), key=lambda x: key.index(x)))
    alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"
    key_table = [char for char in key if char in alphabet]
    key_table += [char for char in alphabet if char not in key_table]
    return [key_table[i:i + 5] for i in range(0, len(key_table), 5)]

def find_position(char, key_table):
    for row in range(5):
        for col in range(5):
            if key_table[row][col] == char:
                return row, col
    return None

def process_message(message):
    message = message.upper().replace('J', 'I').replace(' ', '')
    processed = []
    i = 0
    while i < len(message):
        if i == len(message) - 1:
            processed.append(message[i] + 'X')
            i += 1
        elif message[i] == message[i + 1]:
            processed.append(message[i] + 'X')
            i += 1
        else:
            processed.append(message[i] + message[i + 1])
            i += 2
    return processed

def encrypt_message(message, key_table):
    pairs = process_message(message)
    encrypted = []
    for pair in pairs:
        row1, col1 = find_position(pair[0], key_table)
        row2, col2 = find_position(pair[1], key_table)
        if row1 == row2:
            encrypted.append(key_table[row1][(col1 + 1) % 5] + key_table[row2][(col2 + 1) % 5])
        elif col1 == col2:
            encrypted.append(key_table[(row1 + 1) % 5][col1] + key_table[(row2 + 1) % 5][col2])
        else:
            encrypted.append(key_table[row1][col2] + key_table[row2][col1])
    return ''.join(encrypted)

def decrypt_message(encrypted_message, key_table):
    pairs = process_message(encrypted_message)
    decrypted = []
    for pair in pairs:
        row1, col1 = find_position(pair[0], key_table)
        row2, col2 = find_position(pair[1], key_table)
        if row1 == row2:
            decrypted.append(key_table[row1][(col1 - 1) % 5] + key_table[row2][(col2 - 1) % 5])
        elif col1 == col2:
            decrypted.append(key_table[(row1 - 1) % 5][col1] + key_table[(row2 - 1) % 5][col2])
        else:
            decrypted.append(key_table[row1][col2] + key_table[row2][col1])
    return ''.join(decrypted)

def main():
    key = input("Enter the key: ")
    message = input("Enter the message: ")
    key_table = generate_key_table(key)
    
    print("Generated Key Table:")
    for row in key_table:
        print(' '.join(row))
    
    encrypted_message = encrypt_message(message, key_table)
    print("Encrypted Message:", encrypted_message)
    
    decrypted_message = decrypt_message(encrypted_message, key_table)
    print("Decrypted Message:", decrypted_message)

if __name__ == "__main__":
    main()
