/*******************************************************************************
 * Copyright (C) 2013 Snowdream Mobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.snowdream.wallpaper;

import android.os.Bundle;
import android.widget.AbsListView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date 2013-6-10
 */
public class AbsListViewBaseActivity extends SlidingFragmentActivity {

    protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";

    protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

    protected AbsListView listView;

    protected boolean pauseOnScroll = false;

    protected boolean pauseOnFling = true;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL, false);
        pauseOnFling = savedInstanceState.getBoolean(STATE_PAUSE_ON_FLING, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        applyScrollListener();
    }

    private void applyScrollListener() {
        if (listView != null) {
            listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll,
                    pauseOnFling));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
        outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
    }

    /*
     * @Override public boolean onPrepareOptionsMenu(Menu menu) { MenuItem
     * pauseOnScrollItem = menu.findItem(R.id.item_pause_on_scroll);
     * pauseOnScrollItem.setVisible(true);
     * pauseOnScrollItem.setChecked(pauseOnScroll); MenuItem pauseOnFlingItem =
     * menu.findItem(R.id.item_pause_on_fling);
     * pauseOnFlingItem.setVisible(true);
     * pauseOnFlingItem.setChecked(pauseOnFling); return true; }
     * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
     * (item.getItemId()) { case R.id.item_pause_on_scroll: pauseOnScroll =
     * !pauseOnScroll; item.setChecked(pauseOnScroll); applyScrollListener();
     * return true; case R.id.item_pause_on_fling: pauseOnFling = !pauseOnFling;
     * item.setChecked(pauseOnFling); applyScrollListener(); return true;
     * default: return super.onOptionsItemSelected(item); } }
     */

    @Override
    protected void onStart() {
        super.onStart();
        //Get an Analytics tracker to report app starts & uncaught exceptions etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }
}
