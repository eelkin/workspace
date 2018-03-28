import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assembler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 1) {
			System.err.println("No file given.");
			System.exit(-1);
		}
		
		System.out.println("file: " + args[0]);
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File(args[0]));
		}
		catch (FileNotFoundException ex){
			System.err.println(args[0] + " not found.");
			System.exit(-1);
		}
		
		while(sc.hasNextLine()) {
			System.out.println("==>" + sc.nextLine());
		}
	}

}
