import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class PasswordCheckerUtility {
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException();
	}

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		return (password.equals(passwordConfirm));
	}

	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() < 6)
			throw new LengthException();
		return true;
	}

	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		Pattern pattern = Pattern.compile("(?=.*[A-Z])");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoUpperAlphaException();
		return true;
	}

	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		Pattern pattern = Pattern.compile("(?=.*[a-z])");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoLowerAlphaException();
		return true;
	}

	public static boolean hasDigit(String password) throws NoDigitException {
		Pattern pattern = Pattern.compile("(?=.*\\d)");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoDigitException();
		return true;
	}

	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoSpecialCharacterException();
		return true;
	}

	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		Pattern pattern = Pattern.compile("((.)\\2{2,})");
		Matcher matcher = pattern.matcher(password);
		if (matcher.find())
			throw new InvalidSequenceException();
		return false;
	}

	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		return (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password)
				&& hasSpecialChar(password) && !NoSameCharInSequence(password));
	}

	public static boolean hasBetweenSixAndNineChars(String password) {
		return (password.length() >= 6 && password.length() <= 9);
	}

	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if(hasBetweenSixAndNineChars(password))
			throw new WeakPasswordException();
		return false;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		for(String password: passwords){
			try {
				isValidPassword(password);
			} catch (LengthException e) {
				invalidPasswords.add(password+" The password must be at least 6 characters long");
			} catch (NoUpperAlphaException e) {
				invalidPasswords.add(password+" The password must contain at least one uppercase alphabetic character");
			} catch (NoLowerAlphaException e) {
				invalidPasswords.add(password+" The password must contain at least one lowercase alphabetic character");
			} catch (NoDigitException e) {
				invalidPasswords.add(password+" The password must contain at least one digit");
			} catch (NoSpecialCharacterException e) {
				invalidPasswords.add(password+" The password must contain at least one special character");
			} catch (InvalidSequenceException e) {
				invalidPasswords.add(password+" The password cannot contain more than two of the same character in sequence.");
			}
		}
		return invalidPasswords;
	}
}
