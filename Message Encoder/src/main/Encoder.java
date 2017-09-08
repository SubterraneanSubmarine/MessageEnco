package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Encoder {
	private static String passphrase;

	public Encoder(String pass) {
		passphrase = pass;
	}

	/**
	 * Retrieves the pass-phrase for any instance of Encoder
	 * 
	 * @return
	 */
	public String getPassphrase() {
		return passphrase;
	}

	/**
	 * Adds the casted char value of the pass-phrase attribute to the sent
	 * string x then returns those numeric values cast back into a string of
	 * characters.
	 * 
	 * This is known to cause some characters to change in value when written to
	 * a file, upon decoding the text written in said file the message will not
	 * return the same as written.
	 * 
	 * @param x
	 * @return
	 */
	public String encode(String x) {
		StringBuilder encoded = new StringBuilder();
		char[] message = x.toCharArray();
		char[] pass = passphrase.toCharArray();
		int z = 0;

		for (int i = 0; i < message.length; i++) {

			encoded.append((char) (message[i] + pass[z]));

			z = (z == pass.length) ? z = 0 : z++;

		}
		return encoded.toString();
	}

	/**
	 * Adds the casted char value of the pass-phrase attribute to the sent
	 * string x then returns those numeric values into a string of characters
	 * separated by spaces.
	 * 
	 * Can be written to files and decoded from files safely
	 * 
	 * @param x
	 * @return
	 */
	public String encodeSafe(String x) {
		StringBuilder encoded = new StringBuilder();
		char[] message = x.toCharArray();
		char[] pass = passphrase.toCharArray();
		int z = 0;

		for (int i = 0; i < message.length; i++) {

			encoded.append(message[i] + pass[z] + " ");

			z++;
			z = (z >= pass.length) ? 0 : z;
		}

		return encoded.toString();
	}

	/**
	 * Writes a message to the specified file address in presumably txt format.
	 * 
	 * @param fileAddress
	 * @param message
	 */
	public void writeTo(String fileAddress, String message) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileAddress));
			char[] chars = message.toCharArray();

			for (int x = 0; x < chars.length; x++) {
				if (chars[x] == '\n') {
					bufferedWriter.newLine();
				} else {
					bufferedWriter.write(chars[x]);
				}
			}

			bufferedWriter.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error in handling request");
		}
	}

}
