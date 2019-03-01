package top.forcebing.borrowhome.common.utils;



public class ResponseBean<T> {

    private int status;
    private String message;
    private T data;

    private ResponseBean() {
    }

    public ResponseBean(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static  ResponseBean success() {
        ResponseBean ResponseBean = new ResponseBean<>();
        ResponseBean.setStatus(1);
        ResponseBean.setMessage("success");
        return ResponseBean;
    }

    public static <T> ResponseBean<T> success(T data) {
        ResponseBean<T> ResponseBean = new ResponseBean<>();
        ResponseBean.setStatus(1);
        ResponseBean.setMessage("success");
        ResponseBean.setData(data);
        return ResponseBean;
    }

    public static <T> ResponseBean<T> success(T data, String msg) {
        ResponseBean<T> ResponseBean = new ResponseBean<>();
        ResponseBean.setStatus(1);
        ResponseBean.setMessage(msg);
        ResponseBean.setData(data);
        return ResponseBean;
    }

    public static <Integer> ResponseBean<Integer> error() {
        ResponseBean<Integer> ResponseBean = new ResponseBean<Integer>();
        ResponseBean.setStatus(0);
        ResponseBean.setMessage("不用慌，问题很大，慌也没用");
        return ResponseBean;
    }


    public static <Integer> ResponseBean<Integer> error(String err) {
        ResponseBean<Integer> ResponseBean = new ResponseBean<Integer>();
        ResponseBean.setStatus(0);
        ResponseBean.setMessage(err);
        return ResponseBean;
    }


    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}