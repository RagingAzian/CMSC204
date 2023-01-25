import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException("The passwords do not match");
	}

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		return (password.equals(passwordConfirm));
	}

	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() < 6)
			throw new LengthException("The password must be at least 6 characters long");
		return true;
	}

	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		Pattern pattern = Pattern.compile("(?=.*[A-Z])");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		return true;
	}

	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		Pattern pattern = Pattern.compile("(?=.*[a-z])");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
		return true;
	}

	public static boolean hasDigit(String password) throws NoDigitException {
		Pattern pattern = Pattern.compile("(?=.*\\d)");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoDigitException("The password must contain at least one digit");
		return true;
	}

	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		return true;
	}

	public static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException {
		Pattern pattern = Pattern.compile("((.)\\2{2,})");
		Matcher matcher = pattern.matcher(password);
		if (matcher.find())
			throw new InvalidSequenceException(
					"The password cannot contain more than two of the same character in sequence.");
		return false;
	}

	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		return (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password)
				&& hasSpecialChar(password) && !NoSameCharInSequence​(password));
	}

	public static boolean hasBetweenSixAndNineChars(String password) {
		return (password.length() >= 6 && password.length() <= 9);
	}

	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if (hasBetweenSixAndNineChars(password))
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
		return false;
	}

}