package com.auto.oneclean;


import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;


import com.auto.oneclean.tools.PhoneCodeBean;
import com.auto.oneclean.tools.ProjectBean;
import com.auto.oneclean.tools.Root;
import com.auto.oneclean.tools.SmsBean;
import com.auto.oneclean.tools.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGet {
    private final String URL_API = "http://39.98.47.121:9091/api ";
    private static final String TAG = "com.aotu.myhttpcet";

    private final String URL_loginName = "wurui";
    private final String URL_password = "wurui456";
    private final String URL_devSecretkey = "8969CAC5DE960CA29E76C0D10CC8D092";
    private final String URL_projectCodeOrName = "腾讯清理大师";
    private final String URL_projectId = "9691";
    private final int URL_msgType = 1;
    private Response response;
    private TextView mTextiew;
    public static String mResponseData = "";
    private User mUser = new User();
    private Root mRoot = new Root();
    private ProjectBean mProjectRoot;
    private SmsBean smsBean;
    SharedPreferences userSettings;

    private static String TOCKEN;

    private static String mProjectId;
    Gson gson;

    public void getData() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String SINGN_URL = URL_API + "/login/v1?" + "loginName=" + URL_loginName + "&password="
                        + URL_password + "&devSecretkey=" + URL_devSecretkey;

                // 登录
                httpRequest(SINGN_URL, 1);
                Log.i(TAG, "        =========== doInBackground: ==========" + mResponseData);

                String TOUCKEN_URL = URL_API + "refreshToken/v1?token=" + TOCKEN;

                String PROJECT_ID_URL = URL_API + "/findProject/v1?" + "token=" + TOCKEN + "&projectCodeOrName=" + URL_projectId;

                String PROJECT_GET_URL = URL_API + "/getUserProject/v1?" + "token=" + TOCKEN;
                if (mRoot.getCode() == "-1")
                    httpRequest(TOUCKEN_URL, 2);
                Log.i(TAG, "        =========== doInBackground: =====2222=====" + mResponseData);
                // 获取项目ID码
                httpRequest(PROJECT_GET_URL, 3);


                //--------------------      读取 token              --

                mProjectId = userSettings.getString("projectId", "");

                //http://39.98.47.121:9091/api/getPhoneNo/v1?token=xxx&projectId=yyy&msgOpType=sj&msgType=1
                String PROJECT_GET_PHONE_URL = URL_API + "/getPhoneNo/v1?token=" + TOCKEN + "&projectId="
                        + mProjectId + "&msgOpType=zdsjh&msgType=1" + "&phoneNo=13812341234";

                // httpRequest(PROJECT_GET_PHONE_URL,4);

                String PROJECT_GET_SMS_URL = URL_API + "/getSms/v1?" + "token=" + TOCKEN + "&projectId="
                        + mProjectId + "&phoneNo=" + "13812341234" + "&msgType=1";
                httpRequest(PROJECT_GET_SMS_URL, 5);

            }
        });

    }


    public void getTocken() {
        Log.e(TAG, "==============           getTocken:           " + TOCKEN);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(URL_API + "refreshToken/v1?token=" + TOCKEN)
                .build();
        final Call call = okHttpClient.newCall(request);

        try {
            response = call.execute();
            mResponseData = response.body().string();
            Log.e(TAG, "run: ===========http Get getTocken ==============" + mResponseData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sloveJSON(String responseData) {

        gson = new Gson();
        Log.i(TAG, "  ==========   sloveJSON:========== " + responseData);
        mRoot = gson.fromJson(responseData, Root.class);
        //User mUser = (User) gson.fromJson(responseData, User.class);
        TOCKEN = mRoot.getUser().getToken();
        String msg = mRoot.getMsg();
        if (mRoot.getCode().equals("0")) {
            TOCKEN = mRoot.getUser().getToken();
        }

        //---------------------------token  保存 -------------
        if (mRoot.getCode() == "-1") {
            getTocken();
        }
    }


    public void projectJSON(String jsonStr) {
        Log.i(TAG, "projectJSON:      jsonStr    " + jsonStr);
        gson = new Gson();
        Type type = new TypeToken<ProjectBean>() {
        }.getType();
        mProjectRoot = gson.fromJson(jsonStr, type);
        System.out.println("==========mProjectRoot=======" + mProjectRoot.msg);
        System.out.println("==========mProjectRoot=======" + mProjectRoot.projects);
        if (mProjectRoot.projects != null) {
            for (int i = 0; i < mProjectRoot.projects.size(); i++) {
                mProjectId = mProjectRoot.projects.get(i).projectId;
                Log.e(TAG, "projectJSON: id            " + mProjectId);
                SharedPreferences.Editor editor = userSettings.edit();
                editor.putString("projectId", mProjectId);
                editor.commit();
            }
        }

        //"msg":"token验证：token信息有误，请重新登录获取"
        if (mRoot.getCode() == "-1") {
            getTocken();
        }
    }

    private PhoneCodeBean mPhoneCodeBean;

    public void phoneJSON(String jsonStr) {
        Log.i(TAG, "  phoneJSON:      jsonStr    " + jsonStr);
        gson = new Gson();
        Type type = new TypeToken<PhoneCodeBean>() {
        }.getType();
        mPhoneCodeBean = gson.fromJson(jsonStr, type);
        Log.e(TAG, "          phoneJSON:    " + mPhoneCodeBean.getPhoneInfo());
        ;
    }

    //85209ca823c642adb44374be7fe7930d
    public void msmJSON(String jsonStr) {
        Log.i(TAG, "  msmJSON:      jsonStr    " + jsonStr);
        gson = new Gson();
        Type type = new TypeToken<SmsBean>() {
        }.getType();
        smsBean = gson.fromJson(jsonStr, type);
        System.out.println("==========msmJSON=======" + smsBean.getMsg());
        System.out.println("==========msmJSON=======" + smsBean.getMsgInfo());
        if (smsBean.getMsgInfo() != null) {
            String textContent = smsBean.getMsgInfo().getMsgTextContent();
            Log.e(TAG, "msmJSON: textContent" + textContent);
        }
    }

    public void tockenUP(String jsonStr) {
        Gson gson = new Gson();
        mRoot = (Root) gson.fromJson(jsonStr, Root.class);
        //mUser = gson.fromJson(jsonStr, User.class);
        Log.e(TAG, "run: ==========get  new  tocken======" + mRoot.getUser().getToken());
        SharedPreferences.Editor editor = userSettings.edit();
        editor.remove("token");
        editor.commit();
        TOCKEN = mRoot.getUser().getToken();
        //---------------------------token  保存 -------------
    }

    /**
     * http 请求
     *
     * @param url
     * @param ID
     */
    public void httpRequest(String url, final int ID) {
        Log.e(TAG, "httpRequest:       TOCKEN             " + TOCKEN);
        Log.e(TAG, "httpRequest: ======     =======" + ID + url);

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        try {
            response = call.execute();
            mResponseData = response.body().string();
            int id = ID;
            Log.e(TAG, "run: ID          " + ID);
            //------------------            token   读取     ---------


            TOCKEN = userSettings.getString("token", "");
            switch (id) {
                case 1:
                    sloveJSON(mResponseData);
                case 2:
                    tockenUP(mResponseData);
                case 3:
                    projectJSON(mResponseData);
                case 4:
                    phoneJSON(mResponseData);
                case 5:
                    msmJSON(mResponseData);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //TOCKEN = mRoot.getUser().getToken();
            Log.e(TAG, "run: ID          " + ID + " ==========" + TOCKEN);
        }
    }


}
