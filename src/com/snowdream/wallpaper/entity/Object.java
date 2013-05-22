
package com.snowdream.wallpaper.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Object implements Parcelable{
    @DatabaseField(id = true)
    protected String id;
    
    public static final int TYPE_OBJECT = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_ALBUM = 2;
    public static final int TYPE_ALBUMS = 3;
    
    protected int type = TYPE_OBJECT;

    public Object() {
        // ORMLite needs a no-arg constructor
    }
    
    public Object(int type){
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
