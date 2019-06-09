package com.geveze.utilities

import android.graphics.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Created by sametoztoprak on 16/12/2017.
 */
class ImageConverter {

    companion object {

        fun getRoundedCornerBitmap(bitmap: Bitmap, pixels: Int): Bitmap {
            val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)

            val color = -0xbdbdbe
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rectF = RectF(rect)
            val roundPx = pixels.toFloat()

            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(bitmap, rect, rect, paint)

            return output
        }

        fun byteArraytoBitmap(imageByteArray: ByteArray?): Bitmap {
            val arrayInputStream = ByteArrayInputStream(imageByteArray)
            val bitmap = BitmapFactory.decodeStream(arrayInputStream)
            return bitmap;
        }

        fun bitmaptoByteArray(image: Bitmap): ByteArray {
            val blob = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
            return blob.toByteArray()

        }
    }
}