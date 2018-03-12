import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of a class or q to quit.");
		while(sc.hasNext()) {
			
			String className = sc.nextLine();
			
			try {
				Class<?> myClass = Class.forName(className);
				printFieldsAndMethods(myClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Enter the name of a class or q to quit.");
		}
		
	}
	
	/*
	public void printFields(Class<?> myClass, boolean isStatic) {
		Field[] fields = myClass.getFields();
		for(int i = 0; i<fields.length; i++) {
			int mod =  fields[i].getModifiers();
			if(Modifier.isStatic(mod) == isStatic && isStatic == false){
				System.out.println(fields[i].getName() + " : " + 
						fields[i].getType() + " - " + 
						fields[i].getDeclaringClass());
			} else {
				System.out.println("S:"+fields[i].getName() + " : " + 
						fields[i].getType() + " - " + 
						fields[i].getDeclaringClass());
			}
		}
	}
	
	public void printMethods(Class<?> myClass, boolean isStatic) {
		Method[] methods = myClass.getMethods();
		for(int i = 0; i<methods.length; i++) {
			int mod =  methods[i].getModifiers();
			Class<?>[] p = methods[i].getParameterTypes();
			String parameters = "";
			for(int j = 0; j < p.length; j++) {
				if(j != p.length - 1) {
					parameters += p[j]+", ";
				} else {
					parameters += p[j];
				}
			}
			if(!Modifier.isStatic(mod)){
				System.out.println(methods[i].getName() + "(" + 
						parameters + ") : " + 
						methods[i].getReturnType() + " - " + 
						methods[i].getDeclaringClass());
			}
		}
	}
	*/
	
	public static void printFieldsAndMethods(Class<?> myClass) {
		Field[] fields = myClass.getFields();
		Method[] methods = myClass.getMethods();
		
		// sorting using anonymous classes
		/*
		Arrays.sort(fields, new Comparator<Field>() {
			public int compare(Field one, Field two) {
				return one.getName().compareTo(two.getName());
			}
		});
		
		Arrays.sort(methods, new Comparator<Method>() {
			public int compare(Method one, Method two) {
				return one.getName().compareTo(two.getName());
			}
		});
		*/
		
		// sorting using lambda functions
		Arrays.sort(fields, (one, two) -> one.getName().compareTo(two.getName()));
		Arrays.sort(methods, (one, two) -> one.getName().compareTo(two.getName()));
		//first print non-static fields
		for(int i = 0; i<fields.length; i++) {
			int mod =  fields[i].getModifiers();
			if(!Modifier.isStatic(mod)){
				System.out.println(fields[i].getName() + " : " + 
						fields[i].getType() + " - " + 
						fields[i].getDeclaringClass());
			}
		}
		
		//next print non-static methods
		for(int i = 0; i<methods.length; i++) {
			int mod =  methods[i].getModifiers();
			Class<?>[] p = methods[i].getParameterTypes();
			String parameters = "";
			for(int j = 0; j < p.length; j++) {
				if(j != p.length - 1) {
					parameters += p[j]+", ";
				} else {
					parameters += p[j];
				}
			}
			if(!Modifier.isStatic(mod)){
				System.out.println(methods[i].getName() + "(" + 
						parameters + ") : " + 
						methods[i].getReturnType() + " - " + 
						methods[i].getDeclaringClass());
			}
		}
		
		//next print static fields and methods
		for(int i = 0; i<fields.length; i++) {
			int mod =  fields[i].getModifiers();
			if(Modifier.isStatic(mod)){
				System.out.println("S:"+fields[i].getName() + " : " + 
						fields[i].getType() + " - " + 
						fields[i].getDeclaringClass());
			}
		}
		
		for(int i = 0; i<methods.length; i++) {
			int mod =  methods[i].getModifiers();
			Class<?>[] p = methods[i].getParameterTypes();
			String parameters = "";
			for(int j = 0; j < p.length; j++) {
				if(j != p.length - 1) {
					parameters += p[j]+", ";
				} else {
					parameters += p[j];
				}
			}
			if(Modifier.isStatic(mod)){
				System.out.println("S:"+methods[i].getName() + "(" + 
						parameters + ") : " + 
						methods[i].getReturnType() + " - " + 
						methods[i].getDeclaringClass());
			}
		}
	}

}
