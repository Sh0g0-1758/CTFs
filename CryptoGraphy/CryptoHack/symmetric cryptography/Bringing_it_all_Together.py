from Crypto.Cipher import AES

N_ROUNDS = 10
key = b'\xc3,\\\xa6\xb5\x80^\x0c\xdb\x8d\xa5z*\xb6\xfe\\'
ciphertext = b'\xd1O\x14j\xa4+O\xb6\xa1\xc4\x08B)\x8f\x12\xdd'

# Initialize AES cipher for decryption
cipher = AES.new(key, AES.MODE_ECB)

# Decrypt the ciphertext block by block
block_size = AES.block_size
plaintext = b''

for i in range(0, len(ciphertext), block_size):
    encrypted_block = ciphertext[i:i + block_size]
    decrypted_block = cipher.decrypt(encrypted_block)
    plaintext += decrypted_block

# Print the decrypted plaintext
print(plaintext.decode('utf-8'))

# flag : crypto{MYAES128}