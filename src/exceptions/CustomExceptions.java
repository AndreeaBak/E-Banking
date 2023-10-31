package exceptions;

public class CustomExceptions extends Exception {
    public static class AccountNotFoundException extends Exception {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    public static class InvalidInput extends Exception {
        public InvalidInput(String message){
            super(message);
        }
    }
}
