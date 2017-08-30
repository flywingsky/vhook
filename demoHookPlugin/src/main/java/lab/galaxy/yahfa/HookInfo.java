package lab.galaxy.yahfa;

import android.app.Application;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by liuruikai756 on 31/03/2017.
 */

public class HookInfo {
    public static  boolean toast=true;
    static {
        System.loadLibrary("helloJni");
    }
    public static Application application;

    public static void init(Application app){
        application=app;
        HermesEventBus.getDefault().connectApp(application, "io.virtualhook");
        testt t = new testt();
        HermesEventBus.getDefault().post("这a是从plugin发送的消息000");
        Log.i("yahfa","eventbus--"+HermesEventBus.getDefault());
    }


    public static String[] hookItemNames = {
        "lab.galaxy.demeHookPlugin.Hook_AssetManager_open",
        "lab.galaxy.demeHookPlugin.Hook_URL_openConnection",
        "lab.galaxy.demeHookPlugin.Hook_File_init",
        "lab.galaxy.demeHookPlugin.Hook_Toast_makeText",
        "lab.galaxy.demeHookPlugin.Hook_Toast_show",
        "lab.galaxy.demeHookPlugin.Hook_TelephonyManager_getDeviceId",
        "lab.galaxy.demeHookPlugin.Hook_Application_onCreate",
    };


    static class  testt {
        public void testt(){
            HermesEventBus.getDefault().register(this);
        }

        @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
        public void showText(String text) {
            Log.i("yahfa","hook--plugin中eventbus 收到消息："+text);
//        Toast.makeText(HomeActivity.this,"t:"+text,Toast.LENGTH_SHORT).show();
        }


    }
}
