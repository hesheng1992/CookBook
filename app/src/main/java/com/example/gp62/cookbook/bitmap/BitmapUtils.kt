package com.example.gp62.cookbook.bitmap

import android.content.Context
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.Element.U8_4
import android.renderscript.ScriptIntrinsicBlur
import android.renderscript.RenderScript




/**
 * Created by GP62 on 2017/8/14.
 */

/**
 * 生成二维码
 */
fun encodeAsBitmap(str: String): Bitmap? {
    var bitmap: Bitmap? = null
    var result: BitMatrix? = null
    val multiFormatWriter = MultiFormatWriter()
    try {
        result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200)
        // 使用 ZXing Android Embedded 要写的代码
        val barcodeEncoder = BarcodeEncoder()
        bitmap = barcodeEncoder.createBitmap(result)
    } catch (e: WriterException) {
        e.printStackTrace()
    } catch (iae: IllegalArgumentException) { // ?
        return null
    }

    // 如果不使用 ZXing Android Embedded 的话，要写的代码

    //        int w = result.getWidth();
    //        int h = result.getHeight();
    //        int[] pixels = new int[w * h];
    //        for (int y = 0; y < h; y++) {
    //            int offset = y * w;
    //            for (int x = 0; x < w; x++) {
    //                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
    //            }
    //        }
    //        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
    //        bitmap.setPixels(pixels,0,100,0,0,w,h);

    return bitmap

}

fun blurBitmap(bitmap: Bitmap ,context :Context): Bitmap {

    //Let's create an empty bitmap with the same size of the bitmap we want to blur
    val outBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

    //Instantiate a new Renderscript
    val rs = RenderScript.create(context)

    //Create an Intrinsic Blur Script using the Renderscript
    val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

    //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
    val allIn = Allocation.createFromBitmap(rs, bitmap)
    val allOut = Allocation.createFromBitmap(rs, outBitmap)

    //Set the radius of the blur
    blurScript.setRadius(25f)

    //Perform the Renderscript
    blurScript.setInput(allIn)
    blurScript.forEach(allOut)

    //Copy the final bitmap created by the out Allocation to the outBitmap
    allOut.copyTo(outBitmap)

    //recycle the original bitmap
    bitmap.recycle()

    //After finishing everything, we destroy the Renderscript.
    rs.destroy()

    return outBitmap
}