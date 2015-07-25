package com.wellsen.modernartui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by admin on 5/30/2015.
 */

public class AlertDialogFragment extends DialogFragment {
    private static final String TAG = "AlertDialogFragment";

    public static AlertDialogFragment newInstance() {
        return new AlertDialogFragment();
    }

    // Build AlertDialogFragment using AlertDialogFragment.Builder
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.dialog_title)
                        // User cannot dismiss dialog by hitting back button
                .setCancelable(false)
                        // Set up No Button
                .setNegativeButton(R.string.dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i(TAG, "closing dialog");
                                dialog.cancel();
                            }
                        })
                        // Set up Yes Button
                .setPositiveButton(R.string.dialog_moma,
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int id) {
                                Log.i(TAG, "closing dialog and calling visitMoma() method");
                                visitMoma();
                            }
                        }).create();
    }

    private void visitMoma() {
        final String URL = "http://www.moma.org/";
        final String CHOOSER_TEXT = "Load " + URL + " with:";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        Intent chooserIntent = Intent.createChooser(intent, CHOOSER_TEXT);
        startActivity(chooserIntent);
    }
}