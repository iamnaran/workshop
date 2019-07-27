package com.android.workshop.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.workshop.R;
import com.android.workshop.helper.UpasargaApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by NaRan on 23,Sep,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 **/

public class UtilitiesFunctions {


    public static List<String> districtsArrayList = new ArrayList<>(Arrays.asList("Achham", "Arghakhanchi", "Baglung", "Baitadi", "Bajhang", "Bajura", "Banke", "Bara", "Bardiya", "Bhaktapur", "Bhojpur", "Chitwan", "Dadeldhura", "Dailekh", "Dang deukhuri", "Darchula", "Dhading", "Dhankuta", "Dhanusa", "Dholkha", "Dolpa", "Doti", "Gorkha", "Gulmi", "Humla", "Ilam", "Jajarkot", "Jhapa", "Jumla", "Kailali", "Kalikot", "Kanchanpur", "Kapilvastu", "Kaski", "Kathmandu", "Kavrepalanchok", "Khotang", "Lalitpur", "Lamjung", "Mahottari", "Makwanpur", "Manang", "Morang", "Mugu", "Mustang", "Myagdi", "Nawalparasi", "Nuwakot", "Okhaldhunga", "Palpa", "Panchthar", "Parbat", "Parsa", "Pyuthan", "Ramechhap", "Rasuwa", "Rautahat", "Rolpa", "Rukum", "Rupandehi", "Salyan", "Sankhuwasabha", "Saptari", "Sarlahi", "Sindhuli", "Sindhupalchok", "Siraha", "Solukhumbu", "Sunsari", "Surkhet", "Syangja", "Tanahu", "Taplejung", "Terhathum", "Udayapur"));




    public static void setTypeFaceNormal(Context context, TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "normal.otf");
        textView.setTypeface(face);
    }

    public static void setTypeFaceBold(Context context, TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "bold.otf");
        textView.setTypeface(face);
    }

    public static void setTypeFaceLight(Context context, TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Light.ttf");
        textView.setTypeface(face);
    }

    public static void setTypeFaceItalic(Context context, TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "italic.ttf");
        textView.setTypeface(face);
    }

    public static Fragment getCurrentTopFragment(FragmentManager fm) {
        int stackCount = fm.getBackStackEntryCount();

        if (stackCount > 0) {
            FragmentManager.BackStackEntry backEntry = fm.getBackStackEntryAt(stackCount - 1);
            return fm.findFragmentByTag(backEntry.getName());
        } else {
            List<Fragment> fragments = fm.getFragments();
            if (fragments != null && fragments.size() > 0) {
                for (Fragment f : fragments) {
                    if (f != null && !f.isHidden()) {
                        return f;
                    }
                }
            }
        }
        return null;
    }


    public String getIndexOfDistrict(String district) {

        int id = districtsArrayList.indexOf(district);

        return String.valueOf(id);
    }





    public static void callDialer(Context context, String phone) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", phone, null));
        context.startActivity(phoneIntent);
    }

    public static void messageContent(Context context, String phone) {
        Intent smsMsgAppVar = new Intent(Intent.ACTION_VIEW);
        smsMsgAppVar.setData(Uri.parse("sms:" + phone));
        smsMsgAppVar.putExtra("sms_body", "your ");
        context.startActivity(smsMsgAppVar);
    }


    public static void directionApiContent(Context context, String unSplitLatLng, String label) {

        if (unSplitLatLng != null) {
            String[] splitLatLng = unSplitLatLng.split(",");
            if (splitLatLng.length > 1) {
                final ArrayList<String> splittingLatLng = new ArrayList<>();
                splittingLatLng.addAll(Arrays.asList(splitLatLng));
                String latitude = splittingLatLng.get(0);
                String longitude = splittingLatLng.get(1);

                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.maps.google.com/?q=@" + latitude + "," + longitude));
                        context.startActivity(browserIntent);
                    }
                }

            } else {
                Toast.makeText(context, "Location is not available", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(context, "Location is not available", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }






    public static File persistImage(Bitmap bitmap, String name) {
        File filesDir = UpasargaApplication.getAppContext().getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e("error", "Error writing bitmap", e);
        }
        return imageFile;
    }


    public static String formatToClientDate(String unformattedDate) {
        String currentTimeZoneDate = "";
        try {
//            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
            Date date = sdf.parse(unformattedDate);
            sdf.setTimeZone(TimeZone.getDefault());
            currentTimeZoneDate = sdf.format(date);
            date = sdf.parse(currentTimeZoneDate);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.ENGLISH);
            currentTimeZoneDate = simpleDateFormat.format(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return currentTimeZoneDate;
    }

    public static String formatToClientDateFull(String unformattedDate) {
        String currentTimeZoneDate = "";
        try {
//            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
            Date date = sdf.parse(unformattedDate);
            sdf.setTimeZone(TimeZone.getDefault());
            currentTimeZoneDate = sdf.format(date);
            date = sdf.parse(currentTimeZoneDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.ENGLISH);
            currentTimeZoneDate = simpleDateFormat.format(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return currentTimeZoneDate;
    }

    public static String durationEnglish(String unformatDate) {
        String currentTime = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            /**
             * convert date from UTC to GMT format
             */
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = dateFormat.parse(unformatDate);
            dateFormat.setTimeZone(TimeZone.getDefault());
            Date serverDate = dateFormat.parse(dateFormat.format(date));
            Date currentDate = new Date();
            long diff = currentDate.getTime() - serverDate.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            if (serverDate.before(currentDate)) {
                if (!String.valueOf(days).equals("0")) {
                    currentTime = days + " days ago";
                    if (days > 30) {
                        currentTime = String.valueOf(days / 30) + " month ago";
                    }
                } else if (!String.valueOf(hours).equals("0")) {
                    currentTime = hours + " hour ago";
                } else if (!String.valueOf(minutes).equals("0")) {
                    currentTime = minutes + " min ago";
                } else {
                    currentTime = "Just now";
                }
            }
        } catch (ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return currentTime;
    }

    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String getPath(Uri uri) {
        String path = null;
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = UpasargaApplication.getAppContext().getContentResolver().query(uri, projection, null, null, null);

        if (cursor == null) {
            path = uri.getPath();
        } else {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }
        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }


    @SuppressLint("DefaultLocale")
    public static String convertSeconds(int sec) {
        int seconds = sec % 60;
        int minutes = sec / 60;
        if (minutes >= 60) {
            int hours = minutes / 60;
            minutes %= 60;
            if (hours >= 24) {
                int days = hours / 24;
                return String.format("%02d:%02d.", minutes, seconds);
            }
            return String.format("%02d:%02d", minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }



    public static void sendRateApplicationIntent(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static boolean createFolder(String path) {
        boolean success = false;
        File file = new File(Environment.getExternalStorageDirectory() + path);
        if (!file.exists()) {
            success = file.mkdirs();
        }
        return success;
    }

    public static boolean clearFolder(String path) {
        boolean delete = false;
        File file = new File(Environment.getExternalStorageDirectory() + path);
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File aFile : fileList) {
                if (aFile.exists()) {
                    delete = aFile.delete();
                }
            }
        }
        return delete;
    }

    public static boolean fileExist(Context mContext, String path){
        boolean fileExists = false;
        File extStore = Environment.getExternalStorageDirectory();
        File myFile = new File(extStore.getAbsolutePath() + path);

        if(myFile.exists()){
            fileExists = true;
        }
        return fileExists;
    }
}
