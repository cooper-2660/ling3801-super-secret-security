# ling3801-super-secret-security

***Note: The following program is not very robust, and is making some very heavy assumptions about formatting.  Please copy/format any keys that are pasted into the `rsa/receiversPublicKey.txt` file similarly to how they appear in the `rsa/public/key.txt` file.  Additionally, this program has not been robustly tested for special characters, so it would be best to just use alphabetical characters.***

## Compilation

To compile the Java project, execute the following command in the terminal:

~~~bash
javac rsa/*.java
~~~

## How To

1. [Generate a key pair](#generate-a-key-pair)
2. [Decrypt](#decrypt-a-message)
3. [Encrypt](#encrypt-a-message)

## Generate a key pair

Before encrypting or dencrypting, you will first need to generate a key-pair.  If you already have a key pair, then you may skip to Decrypting or Encrypting. You may generate a key pair by excuting the following command in the terminal:

~~~bash
java rsa/GenerateKeyPair 2048
~~~

or

~~~bash
java rsa/GenerateKeyPair 1024
~~~

Where `2048` and `1024` are the bit length of the key.  Note: `1024` and `2048` are the preffered key lengths.  Additionally, encrypting with a `2048` key will take several seconds on even short messages.

***Note: The receiver of your message will need your public key once a key pair is generated.  The public key may be found under `rsa/public`.  Simpley send the recipient the entire contents of the `rsa/public/key.txt` file.***

## Decrypt a message

Decrypt a message with the following steps:

1. Copy the ciphertext into the `rsa/text/ciphertext.txt` file
2. Execute the following command in the terminal:

~~~bash
java rsa/Decrypt
~~~

The plaintext message will then be copied into the `rsa/text/plaintext.txt` file.

## Encrypt a message

Encrypt a message with the following steps:

1. Write the message to send in the `rsa/text/plaintext.txt` file
2. Copy the recipients public key into `rsa/receiversPublicKey.txt` file
3. Execute the following command in the terminal:

~~~bash
java rsa/Encrypt
~~~

The ciphertext message will then be copied into the `rsa/text/ciphertext.txt` file.
