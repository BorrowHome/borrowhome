package top.forcebing.borrowhome.wxapi.utils;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  15:08
 **/
@Service
public class QueryService {

    public String getYbApi(String apiName,String query) throws IOException {
        String url = "https://api.weixin.qq.com/" + apiName;
        String charset = "UTF-8";
        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String read;

        while((read=br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }

    public String postYbApi(String apiName,String query) throws IOException {
        String url = "https://api.weixin.qq.com/" + apiName;
        String charset = "UTF-8";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.print(query);
        out.flush();
        InputStream response = connection.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String read;

        while((read=br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }
}
