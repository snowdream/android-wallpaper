package com.snowdream.wallpaper;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.github.snowdream.android.util.Log;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImageGridAdapter;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Image;
import com.snowdream.wallpaper.task.ITaskImpl;
import com.snowdream.wallpaper.task.ITaskListener;

public class ImageGridFragment extends Fragment {
	DisplayImageOptions options;
	GridView mGridView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initUI();
		initData();

		return mGridView;
	}

	private void initData() {
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory()
				.cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();

		Bundle bundle = this.getArguments();
		if (bundle != null) {
			Album album = bundle.getParcelable(Extra.ALBUM);
			loadAlbum(album);
		}
	}

	private GridView initUI() {
		mGridView = (GridView) getActivity().getLayoutInflater().inflate(
				R.layout.list_grid, null);
		mGridView.setBackgroundResource(android.R.color.black);
		mGridView.setAdapter(new ImageGridAdapter());
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (getActivity() == null)
					return;

				Image image = (Image) view.getTag();
				if (image == null)
					return;

				ListAdapter adapter = mGridView.getAdapter();
				if ((adapter == null) || !(adapter instanceof ImageGridAdapter)) {
					return;
				}

				ImageGridActivity activity = (ImageGridActivity) getActivity();

				Intent intent = new Intent(activity, ImagePagerActivity.class);
				intent.putParcelableArrayListExtra(Extra.IMAGES,
						(ArrayList<? extends Parcelable>) ((ImageGridAdapter)adapter).getList());
				intent.putExtra(Extra.IMAGE_POSITION, position);
				startActivity(intent);
			}
		});
		return mGridView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private void loadAlbum(Album album) {
		if (album == null) {
			return;
		}

		ITaskImpl iTaskImpl = new ITaskImpl(getActivity(), album,
				new ITaskListener() {

					@Override
					public void onSuccess(List<Image> images) {
						Log.i("onSuccess" + images.toString());
						if (getActivity() == null) {
							Log.w("getActivity() return null!");
							return;
						}
						
						ImageGridAdapter adapter = new ImageGridAdapter(
								getActivity(), images, options);
						mGridView.setAdapter(adapter);
					}

					@Override
					public void onStart() {
						Log.i("onStart");
					}

					@Override
					public void onFinish() {
						Log.i("onFinish");
					}

					@Override
					public void onFailed(Exception e) {
						Log.e("onFailed :" + e.toString());
					}
				});

		new Thread(iTaskImpl).start();

	}
}
