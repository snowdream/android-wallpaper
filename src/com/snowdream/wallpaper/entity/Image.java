
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

public class Image extends Object {
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

    public Image() {
        // ORMLite needs a no-arg constructor
    }
}
