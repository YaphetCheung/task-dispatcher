package priv.yanfei.task.exception;

import priv.yanfei.task.common.enums.IErrorCode;

/**
 * 运行时异常(非检查异常)
 */
public abstract class BaseException extends RuntimeException {

    /**
     * 错误码
     */
    protected IErrorCode errCode;
    /**
     * 是否可重试
     */
    protected boolean isCanRetry = true;

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public BaseException(String message) {
        this(message, null);
    }

    public IErrorCode getErrCode() {
        return errCode;
    }

    public boolean isCanRetry() {
        return isCanRetry;
    }
}
