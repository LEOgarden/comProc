package android.leo.electricity.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leo on 2017/7/19.
 */

public class OkHttpUtil {
    private static final byte[] LOCKER = new byte[0];
    private static OkHttpUtil mInstance;
    private static OkHttpClient mOkHttpClient;

    public OkHttpUtil() {
//        okhttp3.OkHttpClient.Builder clientBuilder=new okhttp3.OkHttpClient.Builder();
//        clientBuilder.readTimeout(30, TimeUnit.SECONDS);//读取超时
//        clientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
//        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 登录网络请求
     * @请求方式 post
     * @param url
     * @param phoneno
     * @param pwd
     * @param callback
     * @throws IOException
     */
    public static void loginPost(String url, String phoneno, String pwd, Callback callback) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phoneno", phoneno);
        builder.add("password", pwd);
        FormBody body = builder.build();
        Request request = new Request.Builder().
                url(url).
                post(body).
                build();
        mOkHttpClient.
                newCall(request).
                enqueue(callback);
    }

    /**
     * 验证码请求
     * @请求方式 post
     * @param url
     * @param phone
     * @param callback
     */
    public static void valCodePost(final String url, final String phone, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phoneno", phone);
        FormBody body = builder.build();
        String str = null;
        Request request = new Request.Builder().
                url(url).
                post(body).
                build();

        mOkHttpClient.
                    newCall(request).
                    enqueue(callback);
    }

    /**
     * 注册网络请求
     * @请求方式 post
     * @param url
     * @param phone
     * @param password
     * @param valCode
     * @param callback
     */
    public static void registerPost(final String url, final String phone, String password, String valCode, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phoneno", phone);
        builder.add("password", password);
        builder.add("vCode", valCode);
        FormBody body = builder.build();
        Request request = new Request.Builder().
                url(url).
                post(body).
                build();

        mOkHttpClient.
                newCall(request).
                enqueue(callback);
    }

    /**
     * 获取服务网点
     * @请求方式 post
     * @param url
     * @param token
     * @param callback
     */
    public static void serverPointPost(final String url, String token, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("token", token);
        FormBody body = builder.build();
        Request request = new Request.Builder().
                url(url).
                post(body).
                build();

        mOkHttpClient.
                newCall(request).
                enqueue(callback);
    }

}
