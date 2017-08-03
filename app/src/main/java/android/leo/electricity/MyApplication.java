package android.leo.electricity;

import android.app.Application;
import android.content.Context;
import android.leo.electricity.utils.SharedPerferencesUtil;
import android.media.session.MediaSession;

/**
 * Created by Leo on 2017/7/6.
 */

public class MyApplication extends Application {
    private static Context mContext ;

    public static String token=null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        token = (String) SharedPerferencesUtil.getParam(mContext, "token", "");
    }

    public static Context getInstance() {
        return mContext;
    }

}
