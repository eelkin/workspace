/*
 * Shannon Duvall and Evan Elkin
 * This object does basic reflection functions
 */
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtilities {

	/* Given a class and an object, tell whether or not the object represents 
	 * either an int or an Integer, and the class is also either int or Integer.
	 * This is really yucky, but the reflection things we need like Class.isInstance(arg)
	 * just don't work when the arg is a primitive.  Luckily, we're only worrying with ints.
	 * This method works - don't change it.
	 * 
	 * Note that if the inputs aren't integers at all it just returns false.
	 */
	private static boolean typesMatchInts(Class<?> maybeIntClass, Object maybeIntObj){
		//System.out.println("I'm checking on "+maybeIntObj);
		//System.out.println(maybeIntObj.getClass());
		try{
			return (maybeIntClass == int.class) &&
					(int.class.isAssignableFrom(maybeIntObj.getClass()) || 
							maybeIntObj.getClass()==Class.forName("java.lang.Integer"));
		}
		catch(ClassNotFoundException e){
			return false;
		}
	}

	/*
	 * TODO: typesMatch
	 * Takes an array of Classes and an array of Objects.
	 * This method should return true if and only if the following 
	 * two things are true:
	 * 1) The 2 arrays are the same length
	 * 2) The ith Object has a type equal to the ith Class.
	 * 
	 * For examples:
	 * typesMatch([String.class, int.class], ["hey", 3]) returns true
	 * typesMatch([],[]) returns true
	 * typesMatch([String.class], [3]) returns false
	 * typesMatch([String.class, String.class], ["hey"]) returns false
	 * 
	 * Note: call my typesMatchInts method to see if an object and class
	 * match as int types.  If it returns false, check if they match 
	 * using isInstance.
	 */
	public static boolean typesMatch(Class<?>[] formals, Object[] actuals){
		if(formals.length != actuals.length) {
			return false;
		}
		//makes sure that all the formals and actuals match up in types
		for(int i = 0; i < formals.length; i++) {
			if(!typesMatchInts(formals[i], actuals[i])) {
				if(!formals[i].isInstance(actuals[i])) {
					return false;
				}
			}
		}
		return true;
	}


	/*
	 * TODO: createInstance
	 * Given String representing fully qualified name of a class and the
	 * actual parameters for a constructor, 
	 * returns initialized instance of the corresponding 
	 * class using the matching constructor.  
	 * 
	 * Examples:
	 * createInstance("java.lang.String", []) returns an empty String.
	 * createInstance("java.lang.Integer", [3]) returns a new Integer, 3.
	 * 
	 * You need to use typeMatch to do this correctly.  Use the class to 
	 * get all the Constructors, then check each one to see if the types of the
	 * constructor parameters match the actual parameters given.
	 */
	public static Object createInstance(String name, Object[] args){
		try {
			Class<?> myClass = Class.forName(name);
			Constructor<?>[] constructors = myClass.getConstructors();
			for(int i = 0; i < constructors.length; i++) {
				Class<?>[] params = constructors[i].getParameterTypes();
				//checks to see if using the right constructor by comparing formals of desired constructor and actuals of utilized constructor
				//if all is good, then it creates a new instance
				if(typesMatch(params, args)) {
					try {
						return constructors[i].newInstance(args);
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						System.out.println("I'm sorry, but I could not instantiate your object. Please check your code and try again");
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						System.out.println("I'm sorry, but I could not instantiate your object. Please check your code and try again");					} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							System.out.println("I'm sorry, but I could not instantiate your object. Please check your code and try again");					}
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("I'm sorry, but I could not find the class. Please try again with a different class type.");
		}
		return null;
	}

	/*
	 * TODO: callMethod
	 * Given a target object with a method of the given name that takes 
	 * the given actual parameters, call the named method on that object 
	 * and return the result. 
	 * 
	 * If the method's return type is void, null is returned, but the 
	 * method should still be invoked.
	 * 
	 * Again, to do this correctly, you should get a list of the object's 
	 * methods that match the method name you are given.  Then check each one to 
	 * see which has formal parameters to match the actual parameters given.  When
	 * you find one that matches, invoke it.
	 */
	public static Object callMethod (Object target, String methodName, Object[] args)
	{
		try {
			Class myClass = target.getClass();
			List<Method> mColl = Arrays.asList(myClass.getMethods());
			//filters through all methods of class to find desired method that user is trying to use
			//proceeds to check through formals of filtered methods to find the method the user wants to use
			Method[] methods = mColl.stream()
					.filter(x->x.getName().equals(methodName))
					.toArray(size -> new Method[size]);
			for(int i = 0; i < methods.length; i++) {
				Class<?>[] params = methods[i].getParameterTypes();		
				if(typesMatch(params, args)) {
					try {
						return methods[i].invoke(target, args);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						System.out.println("I'm sorry, but I was unable to call the method. Please check your code and try again.");
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						System.out.println("I'm sorry, but I was unable to call the method. Please check your code and try again.");
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						System.out.println("I'm sorry, but I was unable to call the method. Please check your code and try again.");
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("You just tried calling a method for an object that doesn't exist. Try calling your object.");
		}

		return null;
	}

}
