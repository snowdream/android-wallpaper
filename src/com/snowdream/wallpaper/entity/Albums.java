
package com.snowdream.wallpaper.entity;

import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Albums extends Object {

    @DatabaseField
    protected String name;

    @DatabaseField
    protected String created_by;

    @DatabaseField
    protected String uuid;

    @DatabaseField
    protected String created_at;

    @ForeignCollectionField(eager = false)
    protected Collection<Album> albums;

    public Albums() {
        super(TYPE_ALBUMS);
        // ORMLite needs a no-arg constructor
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
     * @return the albums
     */
    public Collection<Album> getAlbums() {
        return albums;
    }

    /**
     * @param albums the albums to set
     */
    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }
}
