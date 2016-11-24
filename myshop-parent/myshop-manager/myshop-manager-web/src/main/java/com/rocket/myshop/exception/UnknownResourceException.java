package com.rocket.myshop.exception;

/**
 * Simulated business-logic exception indicating a desired business entity or record cannot be found.
 */
public class UnknownResourceException extends RuntimeException {

	private static final long serialVersionUID = -4481999965313538081L;

	public UnknownResourceException(String msg) {
        super(msg);
    }
}
