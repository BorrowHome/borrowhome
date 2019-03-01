package top.forcebing.borrowhome.common.constant;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2018/12/2  15:27
 **/
public final class Constants {

    public static final String ORDER_SENDING="0";
    public static final String  ORDER_SENDING_SUCCESS=  "1" ;
    public static final String ORDER_SENDING_FAILURE="2";
    public static  final  String ORDER_SENDING_FAILURE_ALL="3";//一直投递失败则由人自己控制整个订单
    public static final int ORDER_TIMEOUT = 1;/*min*/
    public static final int MAX_TRY_COUNT = 3;


}
