package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Decoder {
	private static String passphrase;

	public Decoder(String pass) {
		passphrase = pass;
	}

	/**
	 * Returns the pass-phrase of any instance of decoder
	 * 
	 * @return
	 */
	public String getPassphrase() {
		return passphrase;
	}

	/**
	 * Subtracts the casted char value of the pass-phrase attribute from the
	 * sent string x encoded message then returns those numeric values cast back
	 * into a string of characters.
	 * 
	 * This is known to cause some characters to change in value when read from
	 * a file, the message will not return the same as encoded.
	 * 
	 * @param x
	 * @return
	 */
	public String decode(String x) {
		StringBuilder encoded = new StringBuilder();
		char[] message = x.toCharArray();
		char[] pass = passphrase.toCharArray();
		int z = 0;

		for (int i = 0; i < message.length; i++) {

			encoded.append((char) (message[i] - pass[z]));

			z = (z == pass.length) ? z = 0 : z++;

		}
		return encoded.toString();
	}

	/**
	 * Subtracts the casted char value of the pass-phrase attribute from the
	 * sent string x encoded message then returns those numeric values into a
	 * string of characters
	 * 
	 * Can be read from files and decoded safely
	 * 
	 * @param x
	 * @return
	 */
	public String decodeSafe(String x) {
		StringBuilder numchunk = new StringBuilder();
		StringBuilder decoded = new StringBuilder();
		int numcode;
		char[] message = x.toCharArray();
		char[] pass = passphrase.toCharArray();

		int z = 0;

		for (int i = 0; i < message.length; i++) {

			if (message[i] == ' ') {
				numcode = Integer.parseInt(numchunk.toString());
				numcode -= pass[z];
				decoded.append((char) numcode);
				numchunk.delete(0, message[i]);
				z++;
			} else {
				numchunk.append(message[i]);
			}
			z = (z >= pass.length) ? 0 : z;
		}

		return decoded.toString();
	}

	/**
	 * Reads a message from the specified file address in presumably txt format.
	 * 
	 * @param fileAddress
	 * @param message
	 */
	public String readFrom(String fileAddress) {
		StringBuilder message = new StringBuilder();
		String line;

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress));

			while ((line = bufferedReader.readLine()) != null) {
				message.append(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Unable to open file '" + fileAddress + "'");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error in handling request");
		}

		return message.toString();
	}

}