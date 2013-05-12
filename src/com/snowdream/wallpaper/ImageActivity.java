
package com.snowdream.wallpaper;

import com.loopj.android.image.SmartImageView;
import com.snowdream.wallpaper.entity.Image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author snowdream
 */
public class ImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        
        Intent intent = getIntent();
        
        if(intent != null){
            String url = intent.getStringExtra("url");

            SmartImageView imageview = (SmartImageView)findViewById(R.id.my_image);
            imageview.setAdjustViewBounds(true);
            imageview.setImageUrl(url);
        }
    }
    

    public void OnImageClick(View v) {

    }
}
