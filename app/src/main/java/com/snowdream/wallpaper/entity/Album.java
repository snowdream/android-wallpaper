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

package com.snowdream.wallpaper.entity;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class Album extends Object {
    public static final String ALBUMS_ID_FIELD_NAME = "albums_id";

    @DatabaseField
    protected String uuid = null;

    @DatabaseField
    protected String name = null;

    @DatabaseField
    protected String published = null;

    @DatabaseField
    protected String created_by = null;

    @DatabaseField
    protected String updated_by = null;

    @DatabaseField
    protected String created_at = null;

    @DatabaseField
    protected String updated_at = null;

    @ForeignCollectionField(eager = false)
    protected Collection<Image> images = null;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ALBUMS_ID_FIELD_NAME)
    protected Albums albums = null;

    public Album() {
        super(TYPE_ALBUM);
        // ORMLite needs a no-arg constructor
    }

    public Album(Albums albums) {
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(uuid);
        out.writeString(name);
        out.writeString(published);
        out.writeString(created_by);
        out.writeString(updated_by);
        out.writeString(created_at);
        out.writeString(updated_at);

        if (images != null) {
            out.writeInt(images.size());

            for (Image image : images) {
                out.writeParcelable(image, flags);
            }
        }

        out.writeParcelable(albums, 0);
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    private Album(Parcel in) {
        super(in);
        uuid = in.readString();
        name = in.readString();
        published = in.readString();
        created_by = in.readString();
        updated_by = in.readString();
        created_at = in.readString();
        updated_at = in.readString();

        int count = in.readInt();
        if (count > 0) {
            images = new ArrayList<Image>();
        }

        for (int i = 0; i < count; i++) {
            images.add((Image) in.readParcelable(null));
        }

        in.readParcelable(null);
    }
}
