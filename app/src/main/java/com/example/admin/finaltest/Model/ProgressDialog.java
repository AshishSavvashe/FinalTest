package com.example.admin.finaltest.Model;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.admin.finaltest.R;

/**
 * Created by Admin on 04-11-2017.
 */

public class ProgressDialog {
    private static Dialog progressDialog;
    private static TextView tvProgress;
    private static TextView innerProgress;

    public static boolean isShowing() {
        if (progressDialog != null && progressDialog.isShowing()) {
            return true;
        }
        return false;
    }

    /**
     * Shows the progress dialog
     *
     * @param activity
     * @return the {@link TextView} on which progress has to be set
     */
    public static void showProgressDialog(Activity activity) {

        showProgressDialog(activity, activity.getResources().getString(R.string.loading));
    }

    /**
     * Method to show the progress dialog with a message
     *
     * @param activity
     * @param message
     * @return
     */
    public static void showProgressDialog(final Activity activity, final String message) {

        try {
            /* Check if the last instance is alive */
            if (progressDialog != null)
                if (progressDialog.isShowing()) {
                    tvProgress.setText(message);
                    return;
                }

            /*  Ends Here   */

            progressDialog = new Dialog(activity,
                    android.R.style.Theme_Translucent_NoTitleBar);

            progressDialog.setContentView(R.layout.dialog_progress);

            tvProgress = (TextView) progressDialog
                    .findViewById(R.id.tvProgress);
            innerProgress = (TextView) progressDialog
                    .findViewById(R.id.progress);
//            tvProgress.setTypeface(Font.getRegular(activity));
            tvProgress.setText(message);
            innerProgress.setText("");

            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel))
                    .spin();

            Window dialogWindow = progressDialog.getWindow();
            WindowManager.LayoutParams layoutParams = dialogWindow
                    .getAttributes();
            layoutParams.dimAmount = 0.6f;
            dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);

            progressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PROGRESS DIALOG", "EXCEPTION: " + e.getMessage());
        }
    }

    public static void updateProgress(int percentage) {
        innerProgress.setText(Integer.toString(percentage) + "%");
    }

    /**
     * Dismisses the Progress Dialog
     */
    public static boolean dismissProgressDialog() {


        Log.e("PROGRESS DIALOG", "DISMISSED");

        if (progressDialog != null)
            if (progressDialog.isShowing()) {

                try {
                    progressDialog.dismiss();
                } catch (Exception ex) {
                    Log.e("Dismiss Loading Dialog", ex.toString());
                }
                progressDialog = null;
                tvProgress = null;
                innerProgress = null;
                return true;
            }

        return false;
    }
}
