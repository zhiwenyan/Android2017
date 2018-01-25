package zhiwenyan.cmccaifu.com.android2017.retrofit.simple3;

/**
 * Description:
 * Data：1/23/2018-9:38 AM
 *
 * @author: yanzhiwen
 */
public class ErrorHandler {

    public static class ServiceError extends Throwable {
        String errorCode;

        public ServiceError(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
    }

}
