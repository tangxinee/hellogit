package org.seckill.exception;

/**
 * Created by DIY on 2017/4/27.
 */
public class SeckillRepeatException extends SeckillException {
    public SeckillRepeatException(String message) {
	super(message);
    }

    public SeckillRepeatException(Throwable cause) {
	super(cause);
    }
}
