
package com.snowdream.wallpaper.entity;

import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Album extends Object {
    public static final String ALBUMS_ID_FIELD_NAME = "albums_id";

    @DatabaseField
    protected String uuid;

    @DatabaseField
    protected String name;
    
    @DatabaseField
    protected String published;

    @DatabaseField
    protected String created_by;

    @DatabaseField
    protected String updated_by;

    @DatabaseField
    protected String created_at;

    @DatabaseField
    protected String updated_at;

    @ForeignCollectionField(eager = false)
    protected Collection<Image> images;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ALBUMS_ID_FIELD_NAME)
    protected Albums albums;
    
    public Album() {
        super(TYPE_ALBUM);
        // ORMLite needs a no-arg constructor
    }

    
    public Album(Albums albums){
        this.albums = albums;
    }
    
    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the published
     */
    public String getPublished() {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the updated_at
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * @return the images
     */
    public Collection<Image> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(Collection<Image> images) {
        this.images = images;
    }

    /**
     * @return the albums
     */
    public Albums getAlbums() {
        return albums;
    }

    /**
     * @param albums the albums to set
     */
    public void setAlbums(Albums albums) {
        this.albums = albums;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the updated_by
     */
    public String getUpdated_by() {
        return updated_by;
    }


    /**
     * @param updated_by the updated_by to set
     */
    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
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
}
