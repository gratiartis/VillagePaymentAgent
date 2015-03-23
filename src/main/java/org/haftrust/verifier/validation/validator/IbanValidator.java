package org.haftrust.verifier.validation.validator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.haftrust.verifier.validation.constraint.Iban;

/**
 * This IBAN validator validates the following:
 * 
 * <ul>
 * <li>The first 2 characters are the ISO code for a country.</li>
 * <li>The second 2 characters are digits.</li>
 * <li>All letters are upper case.</li>
 * <li>The total length is between 15 and 34 characters.</li>
 * <li>The IBAN meets a MOD-97 check.</li>
 * </ul>
 * 
 * <p>
 * The following notes describe the MOD-97 check.
 * </p>
 * <p>
 * 1. The First 4 digits of the IBAN (Country Code and Check Digit) are moved to
 * the end of the IBAN.
 * </p>
 * <p>
 * i.e. KY12 0123 4567 8901 2345 gives 0123 4567 8901 2345 KY12
 * </p>
 * <p>
 * 2. The letters are converted to numbers per the table shown below.
 * </p>
 * 
 * <pre>
 * A 10  B 11  C 12  D 13  E 14  F 15  G 16  H 17  I 18  J 19  K 20
 * L 21  M 22  N 23  O 24  P 25  Q 26  R 27  S 28  T 29  U 30  V 31
 * W 32  X 33  Y 34  Z 35
 * </pre>
 * <p>
 * i.e. 0123 4567 8901 2345 KY12 gives 0123456789012345203412
 * </p>
 * <p>
 * 3. Divide the resulting number by 97. If the remainder is 1, then the IBAN is
 * valid.
 * </p>
 * 
 * @author Stephen Masters
 */
public class IbanValidator implements ConstraintValidator<Iban, String> {

    // Pattern to check that an IBAN starts with 2 letters (a country code) and
    // 2 digits (the check digit).
    private final Pattern PATTERN = Pattern.compile("[A-Z]{2}[0-9]{2}[A-Z0-9]{10,30}");
    
    private static final Set<String> isoCountries = new HashSet<String>
            (Arrays.asList(Locale.getISOCountries()));
    
    private boolean isLenient;
    
    @Override
    public void initialize(Iban constraint) {
        isLenient = constraint.strictness() == Strictness.LENIENT;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Don't validate a null value.
        // A separate NotEmpty or NotNull annotation should be used to indicate whether the property is compulsory.
        if (value == null) return true;
        
        return isValid(value);
    }
    
    boolean isValid(String value) {
        if (isLenient) {
            value = sanitize(value);
        }
        
        return isValidStructure(value) 
                && isValidCountry(value)
                && isValidMod97(value);
    }
    
    boolean isValidStructure(String value) {
        return PATTERN.matcher(value).matches();
    }
    
    boolean isValidCountry(String value) {
        return isoCountries.contains(value.substring(0, 2));
    }
    
    boolean isValidMod97(String value) {
        // Shift first four characters to the end of the array.
        String shuffledIban = shuffle(value);
        
        // Convert to a numeric form.
        BigInteger checksum = numerize(shuffledIban);
        
        // If the checksum divided by 97 leaves a remainder of 1, the IBAN is valid.
        return new BigInteger(checksum.toString())
                .remainder(new BigInteger("97"))
                .equals(BigInteger.ONE);
    }

    /**
     * Convert the IBAN to a numeric form by replacing each letter with a number
     * as defined in the following table.
     * <pre>
     * A 10  B 11  C 12  D 13  E 14  F 15  G 16  H 17  I 18  J 19  K 20
     * L 21  M 22  N 23  O 24  P 25  Q 26  R 27  S 28  T 29  U 30  V 31
     * W 32  X 33  Y 34  Z 35
     * </pre>
     * i.e.
     * <pre>
     *   12345 => 12345
     *   123C5 => 123125
     * </pre>
     */
    static BigInteger numerize(String iban) {
        StringBuilder sb = new StringBuilder();
        for (char c : iban.toCharArray()) {
            sb.append(Character.getNumericValue(c));
        }
        return new BigInteger(sb.toString());
    }
    
    /**
     * Shift the first 4 characters in the IBAN to the end. i.e.
     * <pre>
     *   KY12 0123 4567 8901 2345 => 0123 4567 8901 2345 KY12
     * </pre>
     */
    static String shuffle(String iban) {
        String shuffledIban = (iban + iban.substring(0, 4));
        shuffledIban = shuffledIban.substring(4, shuffledIban.length());
        return shuffledIban;
    }
    
    static String sanitize(String iban) {
        return iban.toUpperCase().replaceAll(" ", "").replaceAll("-", "");
    }
    
    public static enum Strictness { STRICT, LENIENT; }

}
