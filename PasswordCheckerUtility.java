/*
 * Class: CMSC204
 * Instructor: Professor Monshi
 * Description: (Give a brief description for each Class)
 * Due: 02/07/23
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: __Kaan Sen________
*/
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	private String password;
	private static ArrayList<String> invalidPasswords;
	
	public static void comparePasswords(java.lang.String password, java.lang.String passwordConfirm) throws UnmatchedException 
	{
		if(password.equals(passwordConfirm))
		{
			
		}
		else {
			throw new UnmatchedException("Passwords do not match");
		}
	}
	
	public static boolean comparePasswordsWithReturn(java.lang.String password, java.lang.String passwordConfirm)  
	{
		if(password.equals(passwordConfirm))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isValidLength(java.lang.String password) throws LengthException
	{
		boolean result = false;
		if(password.length() < 6)
		{
			throw new LengthException("The password must be at least 6 characters long");
		}
		else {
			result = true;
		}
		return result;
	}
	
	public static boolean hasUpperAlpha(java.lang.String password) throws NoUpperAlphaException
	{
		boolean result = false;
		for(int i=0; i<password.length(); i++)
		{
			char ch = password.charAt(i);
			if(Character.isUpperCase(ch))
			{
				result = true;
				break;
			}
			
		}
		if(result==false) {
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character"); 
		}
		return result;
	}
	
	public static boolean hasLowerAlpha(java.lang.String password) throws NoLowerAlphaException
	{
		boolean result = false; 
		for(int i=0; i<password.length(); i++)
		{
			char ch = password.charAt(i);
			if(Character.isLowerCase(ch))
			{
				result = true;
				break;
			}
		}
		if(result==false) {
			throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
		}
		return result;
	}
	
	public static boolean hasDigit(java.lang.String password) throws NoDigitException
	{
		boolean result = false;
		for(int i=0; i<password.length(); i++)
		{
			char ch = password.charAt(i);	
			if(Character.isDigit(ch))
			{
				result = true;
				break;
			}
		}
		if(result == false) {
			throw new NoDigitException("The password must contain at least one digit");
		}
		return result;
	}
	
	public static boolean hasSpecialChar(java.lang.String password) throws NoSpecialCharacterException
	{
		Pattern pattern = Pattern.compile("[$&+,:;=\\?@#|/'<>.^*()%!-]");
		Matcher matcher = pattern.matcher(password);
		boolean specialCharacter = matcher.find();
		boolean result = false;
		if(specialCharacter)
		{
			result = true;
		}
		else {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		return result;
	}
	
	public static boolean NoSameCharInSequence(java.lang.String password) throws InvalidSequenceException
	{
		boolean result = true;
		for(int i=0; i<password.length()-2; i++)
		{
			if(password.charAt(i) == password.charAt(i+1) && password.charAt(i) == password.charAt(i+2)) 
				{
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
				}
		}
		return result;
	}
	
	public static boolean hasBetweenSixAndNineChars(java.lang.String password)
	{
		boolean result = false;
		if(password.length() >= 6 && password.length() <= 9)
		{
			result = true;
		}
		else 
		{
			result = false;
		}
		return result;
	}
	
	public static boolean isValidPassword(java.lang.String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{

		if((isValidLength(password)) && (hasUpperAlpha(password)) && (hasLowerAlpha(password)) && (hasDigit(password)) && (NoSameCharInSequence(password)) && (hasSpecialChar(password)))
		{
			return true;
		}
		else {
			return false;
		}
	}

	public static java.util.ArrayList<java.lang.String> getInvalidPasswords(java.util.ArrayList<java.lang.String> passwords) 
	{
		invalidPasswords = new ArrayList<String>();
		String message = null;
		
		for(int i=0; i<passwords.size(); i++)
		{
			try
			{
				isValidPassword(passwords.get(i));
			}
			
			catch(LengthException e)
			{
				message = passwords.get(i) + " The password must be at least 6 characters long";
				invalidPasswords.add(message);
			}
			catch(NoDigitException e)
			{
				message = passwords.get(i) + " The password must contain at least one digit";
				invalidPasswords.add(message);
			}
			catch(NoUpperAlphaException e)
			{
				message = passwords.get(i) + " The password must contain at least one uppercase alphabetic character";
				invalidPasswords.add(message);
			}
			catch(NoLowerAlphaException e)
			{
				message = passwords.get(i) + " The password must contain at least one lowercase alphabetic character";
				invalidPasswords.add(message);
			}
			catch(InvalidSequenceException e)
			{
				message = passwords.get(i) + " The password cannot contain more than two of the same character in sequence";
				invalidPasswords.add(message);
			}
			catch(NoSpecialCharacterException e)
			{
				message = passwords.get(i) + " The password must contain at least one special character";
				invalidPasswords.add(message);
			}
		}
		return invalidPasswords;
	}
	
	public static boolean isWeakPassword(java.lang.String password) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		if(isValidPassword(password) && !hasBetweenSixAndNineChars(password))
		{
			return false;
		}
		else {
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
		}
	}	
}
