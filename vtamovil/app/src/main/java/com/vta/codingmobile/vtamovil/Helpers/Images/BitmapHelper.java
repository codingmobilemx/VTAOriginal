package com.vta.codingmobile.vtamovil.Helpers.Images;

import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.media.ExifInterface;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.vta.codingmobile.vtamovil.BuildConfig;
import com.vta.codingmobile.vtamovil.Helpers.Constants;
import com.vta.codingmobile.vtamovil.Helpers.Permissions.PermissionCamera;
import com.vta.codingmobile.vtamovil.MainActivity;
import com.vta.codingmobile.vtamovil.Model.Clases.ImageSize;
import com.vta.codingmobile.vtamovil.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BitmapHelper {
    private static final String TAG = BitmapHelper.class.getSimpleName();

    public static String userChoosenTask;
    private static int idSolicitud;
    private static int campo;
    private static int idEmpleo;
    private static Boolean aplicaEliminacion = Boolean.FALSE;
    private static String idEmpleoInterno;
    private static String nombreArchivo;
    private static String consulta;
    static String imageFilePath;

    private static File createImageFile() throws IOException {
        File storageDir = MainActivity.context.getFilesDir();
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File image = new File(storageDir, fileName);
        imageFilePath = image.getAbsolutePath();

        return image;
    }


    public static void selectGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        MainActivity.mainActivity.startActivityForResult(Intent.createChooser(intent,
                "Por favor seleccione una imagen..."), Constants.GALLERY);
    }

    static File photoFile = null;

    public static void takePicture() {
        try {
            if (PermissionCamera.checkPermissionCamera()) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(MainActivity.context.getPackageManager()) != null) {

                    try {
                        //photoFile = createImageFile();
                        photoFile = createDirectoryForPictures();

                    } catch (Exception ex) {
                        Log.e(TAG, "Error", ex);
                    }

                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(MainActivity.context, BuildConfig.APPLICATION_ID, photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        MainActivity.mainActivity.startActivityForResult(takePictureIntent, Constants.CAMERA);
                    }
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
        }
    }

    static Uri photoURI;

    private static void cropImageX() {
        final int width = 400;
        final int height = 200;
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            Uri contentUri;

            contentUri = FileProvider.getUriForFile(MainActivity.context, BuildConfig.APPLICATION_ID, photoFile);

            //TODO:  Permission..

            MainActivity.mainActivity.getApplicationContext().grantUriPermission("com.android.camera",
                    contentUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


            cropIntent.setDataAndType(contentUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 2);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", width);
            cropIntent.putExtra("outputY", height);

            cropIntent.putExtra("return-data", true);
            MainActivity.mainActivity.startActivityForResult(cropIntent, Constants.CROP_IMAGE_CAMERA);

        } catch (ActivityNotFoundException a) {
            Log.e("Activity Not Found", "" + a.toString());
        }
    }

    private static void cropImage() {
        try {
            MainActivity.mainActivity.grantUriPermission("com.android.camera", photoURI,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(photoURI, "image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            intent.putExtra("crop", true);
            intent.putExtra("outputX", 512);
            intent.putExtra("outputY", 512);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scaleUpIfNeeded", true);

            MainActivity.mainActivity.startActivityForResult(intent, Constants.CROP_IMAGE_CAMERA);
        } catch (Exception ex) {
            Log.i(TAG, "Error", ex);
        }
    }

    public static void TakePicture() {
        createDirectoryForPictures();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _uri);
        MainActivity.mainActivity.startActivityForResult(intent, Constants.CAMERA);
    }

    private static File createDirectoryForPictures() {
        _file = new File(MainActivity.context.getExternalCacheDir(), UUID.randomUUID().toString() + ".jpg");
        //  _uri = Uri.fromFile(_file);
        return _file;
    }

    public static File _file;
    static Uri _uri;

    private static void CreateDirectoryForPictures() {
        try {
            _file = createImageFile();
            _uri = Uri.fromFile(_file);
        } catch (Exception ex) {

        }
    }


    public static void onCaptureFromCameraResult(Intent data) {
        Bitmap bitmap = null;
        try {
            Log.i(TAG, "Path Camera: " + imageFilePath);
/*
            ImageSize sizeDTO = getSize();
            bitmap = resizeBitmap(imageFilePath, sizeDTO.getWidth(), sizeDTO.getHeight());
            bitmap = setOrientation(bitmap, imageFilePath);

            guardaImage(bitmap, new File(imageFilePath));
            */
            cropImage();

        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
    }

    public static void onSelectFromGalleryResult(Intent data) {
        Bitmap bitmap = null;
        if (data != null) {
            try {

                InputStream inputStream = MainActivity.context.getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                //Cambia la orientación
                String pathImage = getPath(data.getData());
                ImageSize sizeDTO = getSize();

                bitmap = resizeBitmap(pathImage, sizeDTO.getWidth(), sizeDTO.getHeight());
                bitmap = setOrientation(bitmap, pathImage);

                //Reduce el tamaño de la image.
                // bitmap = resizeBitmapInMemory(bitmap, sizeDTO.getWidth(), sizeDTO.getHeight());

                File file = createImageFile();
                Log.i(TAG, "Path Gallery: " + file.getAbsolutePath());
                guardaImage(bitmap, file);

            } catch (IOException e) {
                Log.e(TAG, "Error", e);
            } finally {
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
    }

    private static void guardaImage(Bitmap bitmap, File image) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        } finally {
            try {
                fos.close();
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }
            } catch (IOException e) {
                Log.e(TAG, "Error", e);
            }
        }
    }

    public static void deletePhoto(String imageFilePath) {
        try {
            File file = new File(imageFilePath);
            if (file.exists()) {
                file.delete();
            }

        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
        }
    }

    private static Bitmap setOrientation(Bitmap bitmap, String imageFilePath) throws IOException {
        try {
            ExifInterface ei = new ExifInterface(imageFilePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotate(bitmap, 90);

                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotate(bitmap, 180);

                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotate(bitmap, 270);

                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    return flip(bitmap, true, false);

                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    return flip(bitmap, false, true);

                default:
                    return bitmap;
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
            return null;
        }
    }

    private static Bitmap rotate(Bitmap bitmap, float degrees) {
        Bitmap targetBitmap = null;
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            targetBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception ex) {
            Log.i(TAG, "Error", ex);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return targetBitmap;
    }

    private static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Bitmap targetBitmap = null;
        try {
            Matrix matrix = new Matrix();
            matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
            targetBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception ex) {
            Log.i(TAG, "Error", ex);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return targetBitmap;
    }

    private static String getPath(Uri uri) {
        Context context = MainActivity.context;
        // Documentos Provider
        if (DocumentsContract.isDocumentUri(context, uri)) {

            if (isExternalStorageDocument(uri)) {               // ExternalStorage Provider
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {                // Descargas Provider

                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {                    // Media Provider
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                String selection = "_id=?";
                String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static int calculateSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap resizeBitmap(String path, int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateSize(options, reqWidth, reqHeight);


        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap resizeBitmapInMemory(Bitmap bitmap, int reqWidth, int reqHeight) {
        byte[] bitmapArray = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            bitmapArray = getArrayToBitmap(bitmap);
            BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length, options);

            options.inSampleSize = calculateSize(options, reqWidth, reqHeight);

            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length, options);
        } catch (Exception ex) {
            Log.i(TAG, "Error", ex);
        } finally {
            if (bitmapArray != null) {
                bitmapArray = null;
            }
        }
        return bitmap;
    }

    private static byte[] getArrayToBitmap(Bitmap bitmap) {
        byte[] bitmapData = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            bitmapData = stream.toByteArray();

            stream.close();
        } catch (Exception ex) {
            Log.i(TAG, "Error image", ex);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return bitmapData;
    }

    public static String leeImagenComoBase64(String ruta) {
        String imagenBase64 = "";
        Bitmap bitmap = null;
        try {

            FileInputStream fileInputStream = new FileInputStream(ruta);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            imagenBase64 = Base64.encodeToString(b, Base64.DEFAULT);

            b = null;
            fileInputStream.close();

        } catch (IOException e) {
            Log.e(TAG, "No se pudo leer la imagen. ", e);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return imagenBase64;
    }

    public static ImageSize getSize() {
        WindowManager wm = (WindowManager) MainActivity.context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        DisplayMetrics metrics = MainActivity.context.getResources().getDisplayMetrics();
        float ratio = ((float) metrics.heightPixels / (float) metrics.widthPixels);
        float width = size.x / ratio;
        float height = size.y / ratio;

        ImageSize sizeDTO = new ImageSize();
        sizeDTO.setWidth((int) width);
        sizeDTO.setHeight((int) height);

        return sizeDTO;
    }
}
