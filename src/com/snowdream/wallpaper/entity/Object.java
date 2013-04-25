
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

public class Object {
    @DatabaseField(id = true)
    protected String id;

    public Object() {
        // ORMLite needs a no-arg constructor
    }
}
