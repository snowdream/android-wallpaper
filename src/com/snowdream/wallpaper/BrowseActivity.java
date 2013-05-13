
package com.snowdream.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.image.SmartImageView;

/**
 * @author snowdream
 */
public class BrowseActivity extends Activity {
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
