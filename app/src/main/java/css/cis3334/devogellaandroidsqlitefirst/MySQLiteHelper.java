package css.cis3334.devogellaandroidsqlitefirst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// This class creates the database
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";         // comments on the table
    public static final String COLUMN_ID = "_id";                   // An identification number for a given column
    public static final String COLUMN_COMMENT = "comment";          // comments for given column

    private static final String DATABASE_NAME = "commments.db";     // The name of the database
    private static final int DATABASE_VERSION = 1;                  // The version number of the database

    // Database creation sql statement
    // Sample sql create table command: CREATE TABLE workers (ID long, LastName varchar(50), FirstName varchar(50));
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    // Sets up the database
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Executes database creation sql statement
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Deletes all existing data and recreates the table. Also defines several constants for table name and table columns
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}