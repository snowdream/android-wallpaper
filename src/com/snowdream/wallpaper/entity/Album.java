
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class Album extends Object {

    @DatabaseField
    protected String uuid;

    @DatabaseField
    protected String published;

    @DatabaseField
    protected String created_by;

    @DatabaseField
    protected String updated_at;

    @DatabaseField(foreign = true)
    protected List<Image> images;

    public Album() {
        // ORMLite needs a no-arg constructor
    }
}
