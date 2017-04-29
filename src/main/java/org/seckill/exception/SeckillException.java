package org.seckill.exception;

/**
 * Created by DIY on 2017/4/27.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
	super(message);
    }

    public SeckillException(Throwable cause) {
	super(cause);
    }
}
