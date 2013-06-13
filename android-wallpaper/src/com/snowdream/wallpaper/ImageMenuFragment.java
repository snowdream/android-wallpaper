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

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImageMenuAdapter;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Object;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class ImageMenuFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData(inflater);
        return inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData(LayoutInflater inflater) {
        List<Object> list = new ArrayList<Object>();

		Albums a0 = new Albums();
		a0.setId("1");
		a0.setUuid("c34852ff-d0b7-11e2-8fe6-000c293c165a");
		a0.setName("test");
		list.add(a0);
		
		Album a1 = new Album();
		a1.setId("2");
		a1.setUuid("6fdac02f-d0b4-11e2-83ff-000c293c165a");
		a1.setName("1");
		list.add(a1);

		Album a2 = new Album();
		a2.setId("3");
		a2.setUuid("eb6f46e9-d0b7-11e2-8fe6-000c293c165a");
		a2.setName("2");
		list.add(a2);

		Album a3 = new Album();
		a3.setId("4");
		a3.setUuid("96fba1be-d0b8-11e2-8fe6-000c293c165a");
		a3.setName("3");
		list.add(a3);
        ListAdapter adapter = new ImageMenuAdapter(inflater, list);
        setListAdapter(adapter);

        // init data
        Fragment newContent = new ImageGridFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Extra.OBJECT, a1);
        newContent.setArguments(bundle);

        if (newContent != null)
            switchFragment(newContent);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        Object object = (Object) v.getTag();

        if (object == null) {
            return;
        }

        Fragment newContent = new ImageGridFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Extra.OBJECT, object);
        newContent.setArguments(bundle);

        if (newContent != null)
            switchFragment(newContent);
    }

    // the meat of switching the above fragment
    private void switchFragment(Fragment fragment) {
        if (getActivity() == null)
            return;

        if (getActivity() instanceof ImageGridActivity) {
            ImageGridActivity ra = (ImageGridActivity) getActivity();
            ra.switchContent(fragment);
        }
    }
}
