import java.util.Scanner;

public class Parser {
	private Scanner sc;
	private String line = "";

	public Parser(String fileName) {
		sc = new Scanner(fileName);
		line = sc.nextLine();
	}
	
	public boolean hasMoreCommands() {
		if(sc.hasNextLine()) return true;
		return false;
	}
	
	public void advance() {
		if(hasMoreCommands()) {
			line = sc.nextLine();
		}
	}
	
	public int commandType() {
		return 0;
	}
	
	public String symbol() {
		return "";
	}
	
	public String dest() {
		return "";
	}
	
	public String comp() {
		return "";
	}
	
	public String jump() {
		return "";
	}
}
