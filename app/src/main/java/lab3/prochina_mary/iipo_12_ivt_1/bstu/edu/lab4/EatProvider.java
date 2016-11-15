package lab3.prochina_mary.iipo_12_ivt_1.bstu.edu.lab4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.security.Provider;

/**
 * Created by user on 06.01.2016.
 */
public class EatProvider extends ContentProvider {
    Context cont; //asdasd
    DBhelper dBhelper;
    static final String AUTHORITY = "com.mycontentprovider.eat";
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + "source");
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, "dish", 1);
        uriMatcher.addURI(AUTHORITY, "dishHot", 2);
        uriMatcher.addURI(AUTHORITY, "dishSweet", 3);
    }

    @Override
    public boolean onCreate() {
        dBhelper = new DBhelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        String tables = "dish as DS inner join category as CT on DS.category = CT._id";
        SQLiteDatabase database  = dBhelper.getWritableDatabase();
        switch (uriMatcher.match(uri))
        {
            case 1:
                c = database.query(tables, null, "CT._id = 0", null, null, null, null);
                break;
            case 2:
                c = database.query(tables, null, "CT._id = 1", null, null, null, null);
                break;
            case 3:
                c = database.query(tables, null, "CT._id = 2", null, null, null, null);
                break;
        }
        c.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        long rowId = database.insert("dish", null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
