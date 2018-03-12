import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// set up the input file
		File inputFile = new File("pa1_input.txt");
		File outputFile = new File("output.txt");
		Scanner sc= null;
		
		try {
			sc = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter fw= new FileWriter(outputFile);
			BufferedWriter bw= new BufferedWriter(fw);
			
			while(sc.hasNext()) {
				int[] row = new int[sc.nextInt()];
				for(int i = 0; i < row.length; i++) {
					row[i] = sc.nextInt();
				}
				funSort(row);
				boolean distinctAnswer = isDistinct(row);
				bw.write(distinctAnswer +"\n");
				System.out.println(Arrays.toString(row) + ", " + distinctAnswer);
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean isDistinct(int[] numbers) {
		for(int i = 0; i < numbers.length - 1; i++) {
			for(int j = i + 1; j < numbers.length; j++) {
				if(numbers[i] == numbers[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void funSort(int[] numbers) {
		for(int i = 0; i < numbers.length - 1; i++) {
			int min = i;
			for(int j = i+1; j < numbers.length; j++) {
				if (numbers[j] < numbers[min]) {
					min = j;
				}
			}
			int temp = numbers[i];
			numbers[i] = numbers[min];
			numbers[min] = temp;
		}
	}

}
