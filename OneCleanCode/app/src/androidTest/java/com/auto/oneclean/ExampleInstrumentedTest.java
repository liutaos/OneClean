package com.auto.oneclean;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Processer mProcesser;
    UiDevice mDevice;
    private static final String CLEAN_PKG_NAME = "android.lite.clean";

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.auto.oneclean", appContext.getPackageName());

        mDevice = UiDevice.getInstance(androidx.test.platform.app.InstrumentationRegistry.getInstrumentation());
        mProcesser = new Processer(mDevice, CLEAN_PKG_NAME);

        mProcesser.startApp();
        mProcesser.waitAMonent(3);
        goToOneStart();
        goToHomeInterface();


    }

    /**
     * 清理大师主页
     */
    public void goToHomeInterface() {
        mProcesser.pritLog("====================goToHomeInterface()=====================");

        UiObject userLayout;
        try {
            userLayout = new UiObject(new UiSelector().className("android.widget.LinearLayout")
                    .resourceId("android.lite.clean:id/a6r"));
            userLayout.click();

            goToCloseRedPkg();

        } catch (Exception e) {
            e.printStackTrace();
            mProcesser.waitAMonent(1);
            mDevice.pressBack();
        }

    }


    /**
     * 清理大师首次启动页 点击启动
     */

    public void goToOneStart() {
        mProcesser.pritLog("============== goToOneStart ===================");


        UiObject userOK = new UiObject(new UiSelector().className("android.widget.RelativeLayout")
                .resourceId("android.lite.clean:id/s4"));
        try {
            userOK.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        mProcesser.waitAMonent(3);
        goToUpdate();
    }

    /**
     * 打开红包
     */
    public void goToCloseRedPkg() {
        mProcesser.pritLog("====================goToCloseRedPkg()=====================");
        UiObject userOpenRedPkg, userLayout;
        try {
            if (mProcesser.exitObjById("android.lite.clean:id/x3", 1)) {
                userOpenRedPkg = new UiObject(new UiSelector().className("android.widget.ImageView")
                        .resourceId("android.lite.clean:id/x3"));
                userOpenRedPkg.click();
                mProcesser.waitAMonent(1);
            } else {
                userLayout = new UiObject(new UiSelector().className("android.widget.LinearLayout")
                        .resourceId("android.lite.clean:id/a6r"));
                userLayout.click();
            }
            mProcesser.waitAMonent(1);
        } catch (Exception e) {
            e.printStackTrace();
            mProcesser.waitAMonent(1);
            mDevice.pressBack();
        }
        goSignIn();
    }

    /**
     * 清理大师更新提示 点击忽略
     */
    public void goToUpdate() {
        mProcesser.pritLog("====================goToUpdate()=====================");
        mProcesser.waitAMonent(2);
        try {
            if (mProcesser.exitObjById("android.lite.clean:id/h5", 1)) {
                mDevice.pressBack();
            } else {
                goToHomeInterface();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mProcesser.waitAMonent(1);
            mDevice.pressBack();
        }
    }

    /**
     * 登录注册
     */
    public void goSignIn() {
        UiObject userSignIn;
        mProcesser.pritLog("====================goSignIn()=====================");
        try {
            userSignIn = new UiObject(new UiSelector().className("android.widget.RelativeLayout")
                    .resourceId("android.lite.clean:id/vj"));
            userSignIn.click();

            mProcesser.waitAMonent(30);
            UiObject userSignIns = new UiObject(new UiSelector().className("android.widget.EditText").resourceId("android.lite.clean:id/wj"));
            //userSignIn = new UiObject(new UiSelector().className("android.widget.TextView"));

            while(mProcesser.exitObjById("android.lite.clean:id/vj",1)){
                userSignIn.click();
                mProcesser.waitAMonent(30);
            }
            if(mProcesser.exitObjById("android.lite.clean:id/wj",1)){
                Log.e("   ", "goSignIn: +++++++++++++++ " );
                userSignIns.setText("13812341234");
            }else{
                userSignIn.click();

            }

        } catch (Exception e) {
            e.printStackTrace();
            mProcesser.waitAMonent(1);
            mDevice.pressBack();
        }

    }


}
