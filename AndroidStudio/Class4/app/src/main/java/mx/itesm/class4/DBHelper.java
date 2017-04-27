package mx.itesm.class4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by forne on 31/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "basesita";
    private static final int VERSION = 1;
    private static final String TABLE = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DBHelper(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creation = "CREATE TABLE " + TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "+
                COLUMN_NAME + " TEXT)";
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Prepared statements
        String[] tables = {TABLE};
        db.execSQL("DROP TABLE IF EXIST ?", tables);
        onCreate(db);
    }

    public void save(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        //nullHack - if you want to save a NULL as a value for a column
        //you can specify the name of the column in the middle parameter.
        db.insert(TABLE, null, values);
    }

    public int find(String name){
        //query - method to find stuff
        SQLiteDatabase db = getReadableDatabase();
        String clause = COLUMN_NAME + " = ? ";
        String[] values = {name};
        Cursor cursor = db.query(TABLE, null, clause, values, null, null, null);
        int result = -1;

        if(cursor.moveToFirst()){
            result = cursor.getInt(0);
        }

        return result;
    }

    public int delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String clause = COLUMN_ID + " = ? ";
        String[] args = {id + ""};
        return db.delete(TABLE, clause, args);
    }


}
