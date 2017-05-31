package com.fangku.fyz.modular_me;

/**
 * Created by bowen.ye
 * Date: 2016/8/4
 * Time: 10:59
 */

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Matrix;

public class AlphaFilter {

    public static final int INSIDE_MODE = 0;
    public static final int EDGE_MODE = 1;
    public static final int OUTSIDE_MODE = 2;

    public int findNonOpaque(int x, int y, int width, int height, int[] srcPixels) {
        if(width < height) {
            return findNonOpaqueByX(x, y, width, height, srcPixels);
        } else {
            return findNonOpaqueByY(x, y, width, height, srcPixels);
        }
    }


    public int findNonOpaqueByX(int x, int y, int width, int height, int[] srcPixels) {
        int mode = OUTSIDE_MODE;
        //当前点左右两侧均有边界点出现，则认为当前点在内部或者边框中
        if(findNonOpaqueByXLeft(x, y, width, height, srcPixels) && findNonOpaqueByXRight(x, y, width, height, srcPixels)) {
            mode = INSIDE_MODE;
        }
        int pos = y*width+x;
        if(isMatch(pos, srcPixels)) {
            mode = EDGE_MODE;
        }
        return mode;
    }


    public boolean findNonOpaqueByXLeft(int x, int y, int width, int height, int[] srcPixels) {
        for(int i=0; i < x; i++) {
            int pos = y*width + i;
            if(isMatch(pos, srcPixels)) {
                return true;
            }
        }
        return false;
    }


    public boolean findNonOpaqueByXRight(int x, int y, int width, int height, int[] srcPixels) {
        for(int i= x+1; i < width; i++) {
            int pos = y*width + i;
            if(isMatch(pos, srcPixels)) {
                return true;
            }
        }
        return false;
    }


    public int findNonOpaqueByY(int x, int y, int width, int height, int[] srcPixels) {
        int mode = OUTSIDE_MODE;
        //当前点上下两侧均有边界点出现，则认为当前点在内部或者边框中
        if(findNonOpaqueByYTop(x, y, width, height, srcPixels) && findNonOpaqueByYBottom(x, y, width, height, srcPixels)) {
            mode = INSIDE_MODE;
        }
        int pos = y*width+x;
        if(isMatch(pos, srcPixels)) {
            mode = EDGE_MODE;
        }
        return mode;
    }


    public boolean findNonOpaqueByYTop(int x, int y, int width, int height, int[] srcPixels) {
        for(int i=0; i < y; i++) {
            int pos = i*width + x;
            if(isMatch(pos, srcPixels)) {
                return true;
            }
        }
        return false;
    }


    public boolean findNonOpaqueByYBottom(int x, int y, int width, int height, int[] srcPixels) {
        for(int i=y+1; i < height; i++) {
            int pos = i*width + x;
            if(isMatch(pos, srcPixels)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatch(int pos, int[]srcPixels) {
        int color = srcPixels[pos];
        int alpha = Color.alpha(color);
        //检测是否是边界，针对背景图片选用透明度进行过滤
        if(alpha >= 94 && alpha < 255) {
            return true;
        }
        return false;
    }


    public Bitmap overlay(Bitmap bmp, Bitmap filter)
    {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap overlay = filter;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_4444);
        bitmap.setHasAlpha(true);

        // 对边框图片进行缩放
        int w = overlay.getWidth();
        int h = overlay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap overlayCopy = Bitmap.createBitmap(overlay, 0, 0, w, h, matrix, true);

        int[] srcPixels = new int[width * height];
        int[] layPixels = new int[width * height];
        bmp.getPixels(srcPixels, 0, width, 0, 0, width, height);
        overlayCopy.getPixels(layPixels, 0, width, 0, 0, width, height);

        int pos = 0;
        for (int i = 0; i < height; i++)
        {
            for (int k = 0; k < width; k++)
            {
                pos = i * width + k;

                int mode = findNonOpaque(k, i, width, height, layPixels);
                if(mode == INSIDE_MODE) {
                    srcPixels[pos] = srcPixels[pos];
                    continue;
                } else if(mode == EDGE_MODE){
                    srcPixels[pos] = layPixels[pos];
                } else{
                    srcPixels[pos] = 0;
                    continue;
                }

            }
        }

        bitmap.setPixels(srcPixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}