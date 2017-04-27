package mx.itesm.firstpartialreview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by forne on 12/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "base";
    private static final int VERSION = 1;
    private static final String TABLE = "datos";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";

    public DBHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creation =   "CREATE TABLE "+TABLE+"("+
                            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            COL_NAME+" TEXT)";
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String[] tables = {TABLE};
        db.execSQL("DROP TABLE IF EXIST ?", tables);
        onCreate(db);
    }

    public void add(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public int find(String name){
        SQLiteDatabase db = getReadableDatabase();
        String clause = COL_NAME+" = ?";
        String[] values = {name};
        Cursor c = db.query(TABLE, null, clause, values, null, null, null);

        int result = -1;
        if(c.moveToFirst()){
            result = c.getInt(c.getColumnIndex(COL_ID));
        }
        return result;
    }

    public int delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String clause = COL_ID+" = ?";
        String[] values = {String.valueOf(id)};
        return db.delete(TABLE, clause, values);
    }
}
