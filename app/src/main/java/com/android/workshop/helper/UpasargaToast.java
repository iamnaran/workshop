package com.android.workshop.helper;

import android.widget.Toast;

public class UpasargaToast {

    public static Toast showToastWithMessage(String messageResourceId) {
        Toast toast = Toast.makeText(UpasargaApplication.getAppContext(), messageResourceId, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static Toast showLongToastWithMessage(String messageResourceId) {
        Toast toast = Toast.makeText(UpasargaApplication.getAppContext(), messageResourceId, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

}
