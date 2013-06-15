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

import com.snowdream.find.sexygirls.R;
import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImageMenuAdapter;
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
        
        Albums all = new Albums();
        all.setId("16");
        all.setUuid("5b613b95-d509-11e2-8058-026bc5020299");
        all.setName(getString(R.string.albums_all));
        list.add(all);
        
        
		Albums china = new Albums();
		china.setId("17");
		china.setUuid("62d72f9e-d509-11e2-8058-026bc5020299");
		china.setName(getString(R.string.albums_china));
		list.add(china);
		
		Albums japan = new Albums();
		japan.setId("19");
		japan.setUuid("6e6ee7fc-d509-11e2-8058-026bc5020299");
		japan.setName(getString(R.string.albums_japan));
		list.add(japan);

		Albums korean = new Albums();
		korean.setId("23");
		korean.setUuid("7876dbb4-d50f-11e2-8058-026bc5020299");
		korean.setName(getString(R.string.albums_korean));
		list.add(korean);

		Albums euroan = new Albums();
		euroan.setId("24");
		euroan.setUuid("3658bd6b-d511-11e2-8058-026bc5020299");
		euroan.setName(getString(R.string.albums_euroan));
		list.add(euroan);

		ListAdapter adapter = new ImageMenuAdapter(inflater, list);
        setListAdapter(adapter);

        // init data
        Fragment newContent = new ImageGridFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Extra.OBJECT, all);
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
