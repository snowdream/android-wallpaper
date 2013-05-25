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

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Object implements Parcelable {
    @DatabaseField(id = true)
    protected String id = null;

    public static final int TYPE_OBJECT = 0;

    public static final int TYPE_IMAGE = 1;

    public static final int TYPE_ALBUM = 2;

    public static final int TYPE_ALBUMS = 3;

    protected int type = TYPE_OBJECT;

    public Object() {
        // ORMLite needs a no-arg constructor
    }

    public Object(int type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
    }

    public static final Parcelable.Creator<Object> CREATOR = new Parcelable.Creator<Object>() {
        public Object createFromParcel(Parcel in) {
            return new Object(in);
        }

        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    private Object(Parcel in) {
        id = in.readString();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return id.equals(other.id);
    }
}
