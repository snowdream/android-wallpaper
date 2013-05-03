
package com.snowdream.wallpaper.entity;


import com.j256.ormlite.field.DatabaseField;

public class Object {
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
