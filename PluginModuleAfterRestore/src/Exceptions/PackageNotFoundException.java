package Exceptions;

/**
 * Created by ${BIM} on 24.01.2017.
 */
public class PackageNotFoundException extends Exception{
    private static final long serialVersionUID = -6811842654741494826L;

    public PackageNotFoundException(String mess) {
        super(mess);
    }
}
