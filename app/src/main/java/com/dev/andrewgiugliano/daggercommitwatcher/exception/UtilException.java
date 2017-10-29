package com.dev.andrewgiugliano.daggercommitwatcher.exception;

/**
 * Custom Exception for errors thrown in a Utility class
 */
public class UtilException extends Exception {
    private Exception exception;

    /**
     * Constructor for UtilException
     *
     * @param exception Exception that caused error
     */
    public UtilException(Exception exception) {
        setRootException(exception);
    }

    /**
     * Getter for root exception
     *
     * @return Root Exception
     */
    public Exception getRootException() {
        return this.exception;
    }

    /**
     * Setter for root Exception
     *
     * @param exception Root Exception
     */
    private void setRootException(Exception exception) {
        this.exception = exception;
    }
}
