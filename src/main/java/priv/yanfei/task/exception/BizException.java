package priv.yanfei.task.exception;

public class BizException extends BaseException {
    public BizException(String message, Throwable e) {
        super(message, e);
    }

    public BizException(String message) {
        super(message);
    }
}
