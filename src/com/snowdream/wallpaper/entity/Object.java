
package com.snowdream.wallpaper.entity;

import com.j256.ormlite.field.DatabaseField;

public class Object {
    @DatabaseField(id = true)
    protected String id;

    public Object() {
        // ORMLite needs a no-arg constructor
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
