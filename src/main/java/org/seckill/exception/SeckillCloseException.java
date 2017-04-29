package org.seckill.exception;

/**
 * Created by DIY on 2017/4/27.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
	super(message);
    }
    public SeckillCloseException(Throwable cause) {
	super(cause);
    }
}
