package com.dev.andrewgiugliano.daggercommitwatcher.exception;

/**
 * Custom Exception for errors thrown at the Service Object level
 */
public class ServiceException extends Exception {
    private Exception exception;

    /**
     * Constructor for ServiceException
     *
     * @param exception Exception that caused error
     */
    public ServiceException(Exception exception) {
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
