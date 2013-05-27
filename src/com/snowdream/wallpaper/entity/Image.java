package com.snowdream.wallpaper.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Image extends Object implements Parcelable {
	public static final String ALBUM_ID_FIELD_NAME = "album_id";

	@DatabaseField
	protected String title = null;

	@DatabaseField
	protected String caption = null;

	@DatabaseField
	protected String file_name = null;

	@DatabaseField
	protected String raw_name = null;

	@DatabaseField
	protected String file_ext = null;

	@DatabaseField
	protected String path = null;

	@DatabaseField
	protected String created_at = null;

	@DatabaseField
	protected String url = null;

	@DatabaseField
	protected String thumb = null;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ALBUM_ID_FIELD_NAME)
	protected Album album = null;

	public Image() {
		super(TYPE_IMAGE);
		// ORMLite needs a no-arg constructor
	}

	public Image(Album album) {
		this.album = album;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * @param file_name
	 *            the file_name to set
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * @return the raw_name
	 */
	public String getRaw_name() {
		return raw_name;
	}

	/**
	 * @param raw_name
	 *            the raw_name to set
	 */
	public void setRaw_name(String raw_name) {
		this.raw_name = raw_name;
	}

	/**
	 * @return the file_ext
	 */
	public String getFile_ext() {
		return file_ext;
	}

	/**
	 * @param file_ext
	 *            the file_ext to set
	 */
	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at
	 *            the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the thumb
	 */
	public String getThumb() {
		return thumb;
	}

	/**
	 * @param thumb
	 *            the thumb to set
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(caption);
		out.writeString(file_name);
		out.writeString(raw_name);
		out.writeString(file_ext);
		out.writeString(path);
		out.writeString(created_at);
		out.writeString(url);
		out.writeString(thumb);
		out.writeString(path);
        //out.writeParcelable(album, 0);
	}

	public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
		public Image createFromParcel(Parcel in) {
			return new Image(in);
		}

		public Image[] newArray(int size) {
			return new Image[size];
		}
	};

	private Image(Parcel in) {
		title = in.readString();
		caption = in.readString();
		file_name = in.readString();
		raw_name = in.readString();
		file_ext = in.readString();
		path = in.readString();
		created_at = in.readString();
		url = in.readString();
		thumb = in.readString();
		path = in.readString();
        //album = in.readParcelable(null);
	}
}
