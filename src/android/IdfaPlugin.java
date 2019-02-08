package by.chemerisuk.cordova.advertising;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

public class IdfaPlugin extends CordovaPlugin {

    private static final String TAG = "idfaPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getInfo")) {
            getInfo(callbackContext);
            return true;
        }
        return false;
    }

    protected void getInfo(final CallbackContext callbackContext)  {
        Context context = this.cordova.getActivity().getApplicationContext();
        JSONObject result = new JSONObject();

        try {
            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
            result.put("aaid", info.getId());
            result.put("limitAdTracking", info.isLimitAdTrackingEnabled());
        } catch (Exception e) {
            Log.e(TAG, "getInfo failed", e);
        }
        callbackContext.success(result);
    }
}
