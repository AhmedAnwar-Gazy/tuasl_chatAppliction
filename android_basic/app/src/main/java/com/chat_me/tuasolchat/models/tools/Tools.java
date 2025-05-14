package com.chat_me.tuasolchat.models.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import android.media.ImageReader;
import android.media.ImageWriter;
import android.media.Image;
import android.view.Surface;

public class Tools {
    public static byte[] imageToByteArray(Image image) {
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer(); // Y-plane (grayscale) or first plane
        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        return byteArray;
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray, int width, int height) {
        YuvImage yuvImage = new YuvImage(byteArray, ImageFormat.NV21, width, height, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, os);
        byte[] jpegByteArray = os.toByteArray();
        return BitmapFactory.decodeByteArray(jpegByteArray, 0, jpegByteArray.length);
    }

    public static Image yuvByteArrayToImage(byte[] yuvData, int width, int height, ImageWriter imageWriter) {
        Image image = imageWriter.dequeueInputImage();
        Image.Plane[] planes = image.getPlanes();

        ByteBuffer yBuffer = planes[0].getBuffer();
        yBuffer.put(yuvData, 0, width * height);

        if (planes.length > 1) {
            ByteBuffer uvBuffer = planes[1].getBuffer();
            uvBuffer.put(yuvData, width * height, yuvData.length - (width * height));
        }

        imageWriter.queueInputImage(image);
        return image;
    }

    public static Image byteArrayToImage(byte[] byteArray, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Image image = createImageFromBitmap(bitmap);
        return image;
    }

    private static Image createImageFromBitmap(Bitmap bitmap) {
        Image image;
        try (ImageReader imageReader = ImageReader.newInstance(bitmap.getWidth(), bitmap.getHeight(), ImageFormat.YUV_420_888, 1)) {
            image = imageReader.acquireNextImage();
        }
        return image;
    }

}
