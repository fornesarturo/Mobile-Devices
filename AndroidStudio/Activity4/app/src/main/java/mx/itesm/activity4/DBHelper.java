package mx.itesm.activity4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by forne on 06/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String NAME = "base";
    private static final int VERSION = 1;
    private static final String TABLE = "hobbies";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_HOBBY = "hobby";

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creation = "CREATE TABLE "+TABLE+
                " ("+COLUMN_NAME+" TEXT PRIMARY KEY, "+
                COLUMN_HOBBY+" TEXT)";
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String[] tables = {TABLE};
        db.execSQL("DROP TABLES IF EXIST ?", tables);
        onCreate(db);
    }

    public void save(String name, String hobby){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOBBY, hobby);
        values.put(COLUMN_NAME, name);
        db.insert(TABLE, null, values);
    }

    public String find(String name){
        SQLiteDatabase db = getReadableDatabase();
        String clause = COLUMN_NAME+" = ?";
        String[] args = {name};
        Cursor c = db.query(TABLE, null, clause, args, null, null, null);
        String result = "";

        if(c.moveToFirst()) {
            result = c.getString(c.getColumnIndex(COLUMN_HOBBY));
        }

        return result;
    }

    public int delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        String clause = COLUMN_NAME+" = ?";
        String[] args = {name};
        return db.delete(TABLE, clause, args);
    }
}
