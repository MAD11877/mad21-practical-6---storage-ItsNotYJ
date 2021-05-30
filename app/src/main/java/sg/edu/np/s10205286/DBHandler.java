package sg.edu.np.s10205286;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(name, description, id, followed)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("description", user.description);
        values.put("id", user.id);
        values.put("followed", user.followed);

        SQLiteDatabase db = getWritableDatabase(); // open database
        db.insert("USERS", null, values);
        db.close(); // close database
    }

    public ArrayList<User> getUserList() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        ArrayList<User> tempUsers = new ArrayList<>();
        User temp = null;

        while(cursor.moveToNext()){
            String retrievedName = cursor.getString(0);
            String retrievedDesc = cursor.getString(1);
            int retrievedId = cursor.getInt(2);
            boolean followed = false;

            if(cursor.getInt(3) == 1)
                followed = true;

            temp = new User(retrievedName, retrievedDesc, retrievedId, followed);
            tempUsers.add(temp);
        }

        cursor.close();
        db.close();

        return tempUsers;
    }

    public User locateUser(int index) {
        String statement = "SELECT * FROM " + "USERS" + " WHERE " + "id" + " = " + index;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        User tempUser;
        if (cursor.moveToFirst())
            tempUser = new User(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3) == 1);
        else
            tempUser = null;

        cursor.close();
        db.close();
        return tempUser;
    }

    public void updateUser(User u) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", u.getName());
        values.put("description", u.getDescription());
        values.put("id", u.getId());
        values.put("followed", u.isFollowed());

        db.update("USERS", values,"name = ?", new String[] {u.getName()});
        db.close();
    }
}
