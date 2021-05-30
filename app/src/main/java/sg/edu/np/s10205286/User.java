package sg.edu.np.s10205286;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(String n, String d, int i, boolean f) {
        this.name = n;
        this.description = d;
        this.id = i;
        this.followed = f;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean f) {
        this.followed = f;
    }
}
