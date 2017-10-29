package com.dev.andrewgiugliano.daggercommitwatcher.exception;

/**
 * Custom Exception for errors thrown at the DAO level
 */
public class DaoException extends Exception {
    private Exception exception;

    /**
     * Constructor for DaoException
     *
     * @param exception Exception that caused error
     */
    public DaoException(Exception exception) {
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
