package com.vta.codingmobile.vtamovil.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vta.codingmobile.vtamovil.Helpers.Constants;
import com.vta.codingmobile.vtamovil.Helpers.DataAnnotations.Column;
import com.vta.codingmobile.vtamovil.Helpers.DataAnnotations.Table;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class DBManager extends SQLiteOpenHelper {
    private static final String TAG = DBManager.class.getSimpleName();

    private static DBManager dbInstance = null;

    public static synchronized DBManager getInstance(Context ctx) {
        if (dbInstance == null) {
            dbInstance = new DBManager(ctx.getApplicationContext());
        }
        return dbInstance;
    }

    public DBManager(Context context) {
        super(context.getApplicationContext(), Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private <T> String createTable(T t) {
        try {
            if (getTableName(t.getClass()) == null) {
                return "";
            }
            StringBuilder sql = new StringBuilder("CREATE TABLE ");
            sql.append(getTableName(t.getClass()));
            sql.append(" (");
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Column fieldEntityAnnotation = field.getAnnotation(Column.class);
                if (fieldEntityAnnotation != null) {
                    sql.append(StringUtils.isNotBlank(fieldEntityAnnotation.name()) ? fieldEntityAnnotation.name() : field.getName());
                    sql.append(" ");
                    sql.append(fieldEntityAnnotation.type());
                    if (fieldEntityAnnotation.isPrimaryKey()) {
                        sql.append(" NOT NULL PRIMARY KEY ");
                    }
                    if (fieldEntityAnnotation.isAutoincrement()) {
                        sql.append(" AUTOINCREMENT ");
                    }
                    sql.append(", ");
                }
            }
            sql.delete(sql.length() - 2, sql.length() - 1);
            sql.append(");");
            return sql.toString();
        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
            return "";
        }
    }

    public static String getTableName(Class<?> tClass) {
        Table annotationTable = tClass.getAnnotation(Table.class);
        String table = tClass.getSimpleName();
        if (annotationTable != null) {
            if (!annotationTable.name().equals("")) {
                table = annotationTable.name();
            }
        }
        return table;
    }
}
