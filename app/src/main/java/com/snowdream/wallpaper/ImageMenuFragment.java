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
        all.setId("1");
        all.setUuid("3720a0db-a6ab-11e2-8058-026bc5020299");
        all.setName(getString(R.string.albums_all));
        list.add(all);
        
        
		Albums beauty = new Albums();
		beauty.setId("8");
		beauty.setUuid("64495c71-d409-11e2-8058-026bc5020299");
		beauty.setName(getString(R.string.albums_beauty));
		list.add(beauty);
		
		Albums star = new Albums();
		star.setId("4");
		star.setUuid("3e3cec31-d409-11e2-8058-026bc5020299");
		star.setName(getString(R.string.albums_star));
		list.add(star);

		Albums anime = new Albums();
		anime.setId("7");
		anime.setUuid("51e906f2-d409-11e2-8058-026bc5020299");
		anime.setName(getString(R.string.albums_anime));
		list.add(anime);

		Albums landscape = new Albums();
		landscape.setId("5");
		landscape.setUuid("45c37f69-d409-11e2-8058-026bc5020299");
		landscape.setName(getString(R.string.albums_landscape));
		list.add(landscape);

		Albums movie = new Albums();
        movie.setId("2");
        movie.setUuid("32bbae9e-d409-11e2-8058-026bc5020299");
        movie.setName(getString(R.string.albums_movie));
        list.add(movie);
        
        Albums games = new Albums();
        games.setId("6");
        games.setUuid("4ac64e61-d409-11e2-8058-026bc5020299");
        games.setName(getString(R.string.albums_games));
        list.add(games);
        
        Albums car = new Albums();
        car.setId("3");
        car.setUuid("39449bfc-d409-11e2-8058-026bc5020299");
        car.setName(getString(R.string.albums_car));
        list.add(car);
        
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
