package exception;

/**
 * Created by ${BIM} on 24.01.2017.
 */
public class BadCredentialsException extends Exception  {
    private static final long serialVersionUID = -1692890668624894488L;

    public BadCredentialsException(String mess) {
        super(mess);
    }
}
