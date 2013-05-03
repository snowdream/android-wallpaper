
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

public class Image extends Object {
    public static final String ALBUM_ID_FIELD_NAME = "album_id";

    @DatabaseField
    protected String title;

    @DatabaseField
    protected String caption;

    @DatabaseField
    protected String file_name;

    @DatabaseField
    protected String raw_name;

    @DatabaseField
    protected String file_ext;

    @DatabaseField
    protected String path;

    @DatabaseField
    protected String created_at;

    @DatabaseField
    protected String url;

    @DatabaseField
    protected String thumb;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ALBUM_ID_FIELD_NAME)
    protected Album album;
    
    public Image() {
        // ORMLite needs a no-arg constructor
    }

    public Image(Album album){
        this.album = album;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
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
     * @param caption the caption to set
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
     * @param file_name the file_name to set
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
     * @param raw_name the raw_name to set
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
     * @param file_ext the file_ext to set
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
     * @param path the path to set
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
     * @param created_at the created_at to set
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
     * @param url the url to set
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
     * @param thumb the thumb to set
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
     * @param album the album to set
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}
