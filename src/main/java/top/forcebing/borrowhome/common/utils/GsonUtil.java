package top.forcebing.borrowhome.common.utils;

import com.google.gson.Gson;

public class GsonUtil {
    private static class GsonHolder {
        private static final Gson INSTANCE = new Gson();
    }


    public static Gson getGsonInstance() {
        return GsonHolder.INSTANCE;
    }

    public static String toJson(Object object){
        Gson gson = getGsonInstance();
        return gson.toJson(object);
    }

}
