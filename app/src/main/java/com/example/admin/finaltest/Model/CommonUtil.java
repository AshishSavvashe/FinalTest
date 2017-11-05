package com.example.admin.finaltest.Model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.finaltest.Constant.SharedPreferencesName;
import com.example.admin.finaltest.R;

import org.json.JSONObject;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

/**
 * Created by Admin on 04-11-2017.
 */

public class CommonUtil implements SharedPreferencesName {

    private static AlertDialog singleButtonDialog, alertDialog;

    /**
     * Filter on editText to block space character
     */
    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = " ";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    /**
     * Method to validate email edit text
     */
    public static boolean validateEmail(Context mContext, EditText et) {
        String email = et.getText().toString();
        if (email.isEmpty()) {
            et.setHovered(true);
            et.requestFocus();
            et.setError(mContext.getString(R.string.please_enter_email));
            return false;
        }
        if (!email.matches(Patterns.EMAIL_ADDRESS.toString())) {
            et.setHovered(true);
            et.requestFocus();
            et.setError(mContext.getString(R.string.please_enter_valid_email_id));
            return false;
        }
        return true;
    }


    /**
     * Method to show retrofit error
     */
    public static void showRetrofitError(Activity activity, RetrofitError retrofitError) {

        if (((RetrofitError) retrofitError).getKind() == RetrofitError.Kind.NETWORK)
            showSingleButtonPopup(activity, activity.getResources().getString(R.string.internetConnectionError));
        else {
            try {
                String json = new String(((TypedByteArray) retrofitError.getResponse().getBody()).getBytes());
                showSingleButtonPopup(activity, new JSONObject(json).get("msg").toString());
            } catch (Exception e) {
                e.printStackTrace();
                showSingleButtonPopup(activity, activity.getResources().getString(R.string.error_msg));
            }
        }
    }

    public static void showSingleButtonPopup(Context context, String msg) {
        if (singleButtonDialog == null) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.single_button_dialog, null);
            mBuilder.setView(dialogView);
            singleButtonDialog = mBuilder.create();

            TextView tvMessage = (TextView) dialogView.findViewById(R.id.tvMessage);
            Button btnPositive = (Button) dialogView.findViewById(R.id.btnPositive);

            tvMessage.setText(msg);
            btnPositive.setText(context.getResources().getString(R.string.ok));

            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (singleButtonDialog != null && singleButtonDialog.isShowing())
                        singleButtonDialog.dismiss();
                }
            });
            singleButtonDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    singleButtonDialog = null;
                }
            });
            // mForgotDialog.setCanceledOnTouchOutside(false);
            //mForgotDialog.setCancelable(false);
            singleButtonDialog.show();
        }
    }


}





