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

public class ImageMenuFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initData(inflater);
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initData(LayoutInflater inflater) {
		List<Album> list = new ArrayList<Album>();

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
		
		//init data
		Fragment newContent = new ImageGridFragment();

		Bundle bundle = new Bundle();
		bundle.putParcelable(Extra.ALBUM, a1);
		newContent.setArguments(bundle);

		if (newContent != null)
			switchFragment(newContent);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Album album = (Album) v.getTag();

		if (album == null) {
			return;
		}

		Fragment newContent = new ImageGridFragment();

		Bundle bundle = new Bundle();
		bundle.putParcelable(Extra.ALBUM, album);
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
