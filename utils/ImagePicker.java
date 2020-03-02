package com.scc.threestarlabel.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by SAM on 11/7/2019.
 */
public class ImagePicker {

    private static final String TAG = "ImagePicker";
    private static File mFile;
    private static Uri mUri;

    public static Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);

        mFile = getImageFile(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUri = FileProvider.getUriForFile(context, Constants.PROVIDER_AUTHORITY, mFile);
        } else {
            mUri = Uri.fromFile(mFile);
        }

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1), "Select image from");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    public static Intent getPickImageFromGalleryIntent(@NonNull Context context) {
        Intent chooserIntent = null;

        mFile = getImageFile(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUri = FileProvider.getUriForFile(context, Constants.PROVIDER_AUTHORITY, mFile);
        } else {
            mUri = Uri.fromFile(mFile);
        }

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        List<Intent> intentList = new ArrayList<>();
        intentList = addIntentsToList(context, intentList, pickIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1), "Select image from");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }
        return chooserIntent;
    }

    public static Intent getTackImageFromCameraIntent(@NonNull Context context) {
        Intent chooserIntent = null;

        mFile = getImageFile(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUri = FileProvider.getUriForFile(context, Constants.PROVIDER_AUTHORITY, mFile);
        } else {
            mUri = Uri.fromFile(mFile);
        }

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);

        List<Intent> intentList = new ArrayList<>();
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1), "Take image from");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }

    public static Uri getImageUriFromResult(Context context, int resultCode, Intent imageReturnedIntent) {
        File imageFile = mFile;
        Uri selectedImage = null;
        if (resultCode == AppCompatActivity.RESULT_OK) {
            boolean isCamera = (imageReturnedIntent == null ||
                    imageReturnedIntent.getData() == null ||
                    imageReturnedIntent.getData().toString().contains(imageFile.toString()));
            if (isCamera) {
                selectedImage = Uri.fromFile(imageFile);
            } else {
                selectedImage = imageReturnedIntent.getData();
            }
            Timber.d("selectedImage: %s", selectedImage);
        }
        return selectedImage;
    }

    private static File getImageFile(Context context) {
        //File imageFile = new File(context.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        File imageFile = new File(context.getExternalCacheDir(), "tempImage.jpg");
        imageFile.getParentFile().mkdirs();
        return imageFile;
    }

    public static File bitmapToFile(Context context, Bitmap bitmap, int quality) {
        File file = new File(context.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        OutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);

            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bitmap.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public static Bitmap bitmapOverlayToCenter(Bitmap bitmap, Bitmap overlayBitmap) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        int bitmap2Width = overlayBitmap.getWidth();
        int bitmap2Height = overlayBitmap.getHeight();

        float marginLeft = (float) (bitmapWidth * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmapHeight * 0.5 - bitmap2Height * 0.5);

        Bitmap finalBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, bitmap.getConfig());
        Canvas canvas = new Canvas(finalBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        canvas.drawBitmap(overlayBitmap, marginLeft, marginTop, null);
        return finalBitmap;
    }
}
