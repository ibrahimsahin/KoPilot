package DataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ContractLayer.Nokta_Co;
import EntityLayer.Nokta;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 15.8.2017.
 */

public class Nokta_Data extends DataController<Nokta> {

    public Nokta_Data(Context ctx) {
        super(ctx, new Nokta());
    }


    public List<Nokta> loadFromQuery(String queryStr) throws DefaultException {
        List<Nokta> list = new ArrayList<Nokta>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    list.add((Nokta) CursorToObject(cursor));
                }
                cursor.close();
            }

        } catch (Exception e) {
            throw new DefaultException(e.toString());
        } catch (Throwable e) {
            throw new DefaultException(e.toString());
        } finally {
            db.close();
            return list;
        }
    }


    public Nokta CursorToObject(Cursor cursor) throws DefaultException {
        Nokta dir = new Nokta();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Nokta_Co.c0_id)));
        dir.setNokta_adi(cursor.getString(cursor.getColumnIndex(Nokta_Co.c2_Nokta_adi)));
        dir.setLongtitude(cursor.getDouble(cursor.getColumnIndex(Nokta_Co.c3_Longtitude)));
        dir.setLatitude(cursor.getDouble(cursor.getColumnIndex(Nokta_Co.c4_Latitude)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Nokta_Co.c5_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Nokta_Co.c6_mustid)));

        return dir;
    }


    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM NOKTA WHERE mid = " + mid);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }

    public Boolean deleteOneRecordContentAccToId(String id) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM NOKTA WHERE id = " + id);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }


    public Boolean updateFromContent(List<Nokta> itms) throws DefaultException {
        Boolean status = false;
        int m_id = 0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Nokta kayit : itms) {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Nokta_Co.NOKTA_TABLE;
                String[] whereParameters = {String.valueOf(kayit.getMid())};
                String WhereArgs = "mid=?";
                Log.v("where", tableName + " -" + whereParameters[0]);
                m_id = db.update(tableName, line, WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.v("upda", "bitti2");
                    Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                } else {
                    Log.v("upda", "bitti3");
                    Log.d("DataController", "KayÄ±t guncelleme baÅŸarÄ±sÄ±z !");
                    status = false;
                    throw new DefaultException("DataController-update:Kayit guncellenemedi !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (Throwable e) {
                Log.d("DataController:insert\n", e.getMessage());
                status = false;
                throw new DefaultException("DataController:insert\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }


    public Boolean insertFromContent(List<Nokta> itms) throws DefaultException {
        Boolean status = false;
        Long m_id = 0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Nokta kayit : itms) {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Nokta_Co.NOKTA_TABLE, null, line);
                if (m_id > 0) {
                    status = true;
                    kayit.setMid(m_id);
                } else {
                    status = false;
                    throw new DefaultException("insert:Kayit Eklenemedi, database tablosu hatali !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (Throwable e) {
                Log.d("DataController:insert\n", e.getMessage());
                status = false;
                throw new DefaultException("DataController:insert\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }

    public ContentValues ObjectToContentValues(Nokta dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put(Nokta_Co.c0_id, dir.getId());
            satir.put(Nokta_Co.c2_Nokta_adi, dir.getNokta_adi());
            satir.put(Nokta_Co.c3_Longtitude, dir.getLongtitude());
            satir.put(Nokta_Co.c4_Latitude, dir.getLatitude());
            satir.put(Nokta_Co.c5_mid, dir.getMid());
            satir.put(Nokta_Co.c6_mustid, dir.getMustid());

        } catch (Exception e) {
            throw new DefaultException(e.toString());
        } finally {
            return satir;
        }
    }

}

