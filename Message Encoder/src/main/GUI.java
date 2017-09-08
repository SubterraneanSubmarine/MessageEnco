package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	//Add an adjustable text field based on the size of the window
	//Change the position of the buttons
	private JPanel contentPane;
	private JTextField passphraseField, writetoField, readfromField;
	private JTabbedPane tabbedPane;
	private JLabel encodeaddressLabel, decodeaddressLabel;
	private Encoder encoder;
	private Decoder decoder;
	private String passphrase, temp, inAddress = "C:\\Users\\Public\\Documents\\myMessage.txt",
			outAddress = "C:\\Users\\Public\\Documents\\myMessage.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("Encoder-Decoder");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		setupTab();
		encodeTab();
		decodeTab();
	}

	private void setupTab() {

		JPanel setupPane = new JPanel();
		tabbedPane.addTab("Setup", null, setupPane, null);
		setupPane.setLayout(null);

		JLabel passphraseLabel = new JLabel("Passphrase");
		passphraseLabel.setBounds(30, 20, 200, 20);
		setupPane.add(passphraseLabel);

		JLabel writetoLabel = new JLabel("Write to file address");
		writetoLabel.setBounds(30, 120, 200, 20);
		setupPane.add(writetoLabel);

		JLabel readfromLabel = new JLabel("Read from file address");
		readfromLabel.setBounds(30, 220, 200, 20);
		setupPane.add(readfromLabel);

		JLabel passconfirmationLabel = new JLabel();
		passconfirmationLabel.setBounds(30, 60, 410, 20);
		setupPane.add(passconfirmationLabel);

		JLabel writeconfirmationLabel = new JLabel(outAddress);
		writeconfirmationLabel.setBounds(30, 160, 410, 20);
		setupPane.add(writeconfirmationLabel);

		JLabel readconfirmationLabel = new JLabel(inAddress);
		readconfirmationLabel.setBounds(30, 260, 410, 20);
		setupPane.add(readconfirmationLabel);

		passphraseField = new JTextField();
		passphraseField.setBounds(30, 40, 300, 20);
		setupPane.add(passphraseField);

		writetoField = new JTextField();
		writetoField.setBounds(30, 140, 300, 20);
		setupPane.add(writetoField);
		writetoField.setColumns(10);

		readfromField = new JTextField();
		readfromField.setBounds(30, 240, 300, 20);
		readfromField.setColumns(10);
		setupPane.add(readfromField);

		JButton passphraseBtn = new JButton("Submit");
		passphraseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					temp = passphraseField.getText();
					if (temp.isEmpty()) {
						throw new NullPointerException();
					} else {
						passphrase = temp;
						encoder = new Encoder(passphrase);
						decoder = new Decoder(passphrase);
						passconfirmationLabel.setText(passphrase);
					}
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
				passphraseField.setText("");

			}
		});
		passphraseBtn.setBounds(340, 40, 100, 20);
		setupPane.add(passphraseBtn);

		JButton writetoBtn = new JButton("Change");
		writetoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					temp = writetoField.getText();
					if (temp.isEmpty()) {
						throw new NullPointerException();
					} else {
						outAddress = temp;
						encodeaddressLabel.setText(outAddress);
						writeconfirmationLabel.setText(outAddress);
					}
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
				writetoField.setText("");

			}
		});
		writetoBtn.setBounds(340, 140, 100, 20);
		setupPane.add(writetoBtn);

		JButton readfromBtn = new JButton("Change");
		readfromBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					temp = readfromField.getText();
					if (temp.isEmpty()) {
						throw new NullPointerException();
					} else {
						inAddress = temp;
						decodeaddressLabel.setText(inAddress);
						readconfirmationLabel.setText(inAddress);
					}
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
				readfromField.setText("");
			}
		});
		readfromBtn.setBounds(340, 240, 100, 20);
		setupPane.add(readfromBtn);
	}

	private void encodeTab() {

		JPanel encodePane = new JPanel();
		tabbedPane.addTab("Encode", null, encodePane, null);
		encodePane.setLayout(null);

		JScrollPane encodescrollPane = new JScrollPane();
		encodescrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		encodescrollPane.setBounds(100, 40, 350, 270);
		encodePane.add(encodescrollPane);

		JTextArea encodetextPane = new JTextArea();
		encodescrollPane.setViewportView(encodetextPane);
		encodetextPane.setLineWrap(true);
		encodeaddressLabel = new JLabel(outAddress);
		encodeaddressLabel.setBounds(120, 11, 340, 20);
		encodePane.add(encodeaddressLabel);

		JLabel encomessLabel = new JLabel("Message :");
		encomessLabel.setBounds(30, 51, 60, 20);
		encodePane.add(encomessLabel);

		JButton encodeBtn = new JButton("Encode");
		encodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					encodetextPane.setText(encoder.encodeSafe(encodetextPane.getText()));
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "One of the following errors has occured:"
							+ "\n-Text field is empty" + "\n-Passphrase hasn't been set yet");
				}
			}
		});
		encodeBtn.setBounds(10, 290, 80, 20);
		encodePane.add(encodeBtn);

		JButton writetoButn = new JButton("Write To");
		writetoButn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					encoder.writeTo(outAddress, encodetextPane.getText());
					JOptionPane.showMessageDialog(null, "File written successfully");
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "One of the following errors has occured:"
							+ "\n-Text field is blank" + "\n-Passphrase hasn't been set yet" + "\n-Illegal file path");
				}
			}
		});
		writetoButn.setBounds(10, 11, 100, 20);
		encodePane.add(writetoButn);

		JButton encodeClBtn = new JButton("Clear");
		encodeClBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodetextPane.setText("");
			}
		});
		encodeClBtn.setBounds(10, 260, 80, 20);
		encodePane.add(encodeClBtn);

	}

	private void decodeTab() {

		JPanel decodePane = new JPanel();
		tabbedPane.addTab("Decode", null, decodePane, null);
		decodePane.setLayout(null);

		JLabel decomessLabel = new JLabel("Message :");
		decomessLabel.setBounds(30, 51, 60, 20);
		decodePane.add(decomessLabel);

		JScrollPane decodescrollPane = new JScrollPane();
		decodescrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		decodescrollPane.setBounds(100, 40, 350, 270);
		decodePane.add(decodescrollPane);

		JTextArea decodetextPane = new JTextArea();
		decodescrollPane.setViewportView(decodetextPane);
		decodetextPane.setLineWrap(true);
		decodeaddressLabel = new JLabel(inAddress);
		decodeaddressLabel.setBounds(120, 11, 340, 20);
		decodePane.add(decodeaddressLabel);

		JButton decodeBtn = new JButton("Decode");
		decodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					temp = decoder.decodeSafe(decodetextPane.getText());
					if (temp == null) {
						throw new NullPointerException();
					} else {
						decodetextPane.setText(temp);
					}
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null,
							"One of the following errors has occurred:" + "\n-Text field is empty"
									+ "\n-Passphrase hasn't been set yet" + "\n-Message cannot be decoded any further"
									+ "\n-Encoded message is not in proper format");
				}
			}
		});
		decodeBtn.setBounds(10, 290, 80, 20);
		decodePane.add(decodeBtn);

		JButton btnReadFrom = new JButton("Read from");
		btnReadFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					decodetextPane.setText(decoder.readFrom(inAddress));
				} catch (Exception NullPointerException) {
					JOptionPane.showMessageDialog(null, "One of the following errors has occured:"
							+ "\n-Passphrase hasn't been set yet" + "\n-Illegal file path");
				}
			}
		});
		btnReadFrom.setBounds(10, 11, 100, 20);
		decodePane.add(btnReadFrom);

		JButton decodeClBtn = new JButton("Clear");
		decodeClBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decodetextPane.setText("");
			}
		});
		decodeClBtn.setBounds(10, 260, 80, 20);
		decodePane.add(decodeClBtn);
	}
}
