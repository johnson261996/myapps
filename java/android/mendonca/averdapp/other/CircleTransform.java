package android.mendonca.averdapp.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by johnson on 04-05-2018.
 */

public class CircleTransform extends BitmapTransformation {

    public CircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool,toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if(source == null)
            return null;

        int size = Math.min(source.getWidth(),source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source,x,y,size,size);

        Bitmap result = pool.get(size,size,Bitmap.Config.ARGB_8888);
        if(result == null){
            result = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
        }

        Canvas canavas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canavas.drawCircle(r,r,r,paint);
        return  result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
