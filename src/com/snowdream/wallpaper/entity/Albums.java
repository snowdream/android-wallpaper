
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class Albums extends Object {

    @DatabaseField
    protected String name;

    @DatabaseField
    protected String created_by;

    @DatabaseField
    protected String uuid;

    @DatabaseField
    protected String created_at;

    @DatabaseField
    protected List<Album> albums;

    public Albums() {
        // ORMLite needs a no-arg constructor
    }
}
