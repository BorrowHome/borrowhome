package top.forcebing.borrowhome.common.exception;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/9  21:55
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}