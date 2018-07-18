package com.self.quiz.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.self.quiz.R;

import java.io.File;
import java.security.PublicKey;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/18
 * CopyRight:  JinkeGroup
 */

public class DialogUtils {
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    public static final int CROP_SMALL_PICTURE = 2;
    public static void showChooseDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.choose_title));
        builder.setNegativeButton(context.getString(R.string.cancel),null);
        String[] item = {"本地照片","相机"};
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which){
                    case CHOOSE_PICTURE:
                        Intent album = new Intent(Intent.ACTION_GET_CONTENT);
                        album.setType("image/*");
                        ((Activity)context).startActivityForResult(album,CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE:
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        camera.putExtra(MediaStore.EXTRA_OUTPUT, getPath());
                        ((Activity)context).startActivityForResult(camera,TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    public static Uri getPath(){
         return Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "image.jpg"));
    }
}
