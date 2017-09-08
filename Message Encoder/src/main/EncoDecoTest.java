package main;

public class EncoDecoTest {
//Encoder and decoder are both objects that take a pass-phrase in order to be created.
//This class will obviously be replaced with the GUI, but this proves that the code works.
	
	public static void main(String[] args){
		Encoder in;
		Decoder out;
		String passphrase = "Red Herrings";
		String message = "A red herring is something that misleads "
				+ "or distracts from a relevant or important issue. \n"
				+ "It may be either a logical fallacy or a literary "
				+ "device that leads readers or audiences towards a "
				+ "false conclusion. \nA red herring might be intentionally "
				+ "used, such as in mystery fiction or as part of rhetorical "
				+ "strategies (e.g. in politics), "
				+ "or it could be inadvertently used during argumentation.";
		
		in = new Encoder(passphrase);
		out = new Decoder(passphrase);
		
		System.out.println(in.encodeSafe(message));
		System.out.println();
		in.writeTo("C:\\Users\\Public\\bik.txt", in.encodeSafe(message));
		
		System.out.println(out.readFrom("C:\\Users\\Public\\bik.txt"));
		System.out.println();
		System.out.println(out.decodeSafe(out.readFrom("C:\\Users\\Public\\bik.txt")));
		
	}
}
