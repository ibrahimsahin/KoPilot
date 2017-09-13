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

import ContractLayer.Ziyaret_Co;
import EntityLayer.Ziyaret;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 21.5.2017.
 */

public class Ziyaret_Data extends DataController<Ziyaret> {

    public Ziyaret_Data(Context ctx) {
        super(ctx,new Ziyaret());
    }



    public List<Ziyaret> loadFromQuery(String queryStr) throws DefaultException {
        List<Ziyaret> list = new ArrayList<Ziyaret>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Ziyaret) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new DefaultException(e.toString());
        }catch (Throwable e){
            throw new DefaultException(e.toString());
        }
        finally {
            db.close();
            return list;
        }
    }





    public Ziyaret CursorToObject(Cursor cursor) throws DefaultException {
        Ziyaret dir = new Ziyaret();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Ziyaret_Co.c0_id)));
        dir.setZiyaret_yer_adi(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c2_Ziyaret_yer_adi_)));
        dir.setDirectory_id(cursor.getLong(cursor.getColumnIndex(Ziyaret_Co.c3_Directory_id)));
        dir.setLanguage_id(cursor.getLong(cursor.getColumnIndex(Ziyaret_Co.c4_Language_id_)));
        dir.setX_koor(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c5_X_koor)));
        dir.setY_koor(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c6_Y_koor_)));
        dir.setOtel(cursor.getInt(cursor.getColumnIndex(Ziyaret_Co.c7_Otel_)));
        dir.setZiyaret_yeri_bilgisi(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c8_Ziyaret_yeri_bilgisi_)));

        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Ziyaret_Co.c_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Ziyaret_Co.c_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Ziyaret_Co.c_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Ziyaret_Co.c_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Ziyaret_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Ziyaret_Co.c_mustid)));


        return dir;
    }




    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM ZIYARET WHERE mid = "+mid);
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
            db.execSQL("DELETE FROM ZIYARET WHERE id = "+id);
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




    public Boolean updateFromContent(List<Ziyaret> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Ziyaret kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Ziyaret_Co.ZIYARET_TABLE;
                String[] whereParameters = {String.valueOf(kayit.getMid())};
                String WhereArgs = "mid=?";
                Log.v("where",tableName +" -" +whereParameters[0]);
                m_id = db.update(tableName, line, WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.v("upda","bitti2");
                    Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                } else {
                    Log.v("upda","bitti3");
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
            }
            catch (Throwable e)
            {
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



    public Boolean insertFromContent(List<Ziyaret> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Ziyaret kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Ziyaret_Co.ZIYARET_TABLE, null, line);
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
            }
            catch (Throwable e)
            {
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

    public ContentValues ObjectToContentValues(Ziyaret dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Ziyaret_Co.c0_id,dir.getId());
            satir.put(Ziyaret_Co.c2_Ziyaret_yer_adi_,dir.getZiyaret_yer_adi());
            satir.put(Ziyaret_Co.c3_Directory_id,dir.getDirectory_id());
            satir.put(Ziyaret_Co.c4_Language_id_,dir.getLanguage_id());
            satir.put(Ziyaret_Co.c5_X_koor,dir.getX_koor());
            satir.put(Ziyaret_Co.c6_Y_koor_,dir.getY_koor());
            satir.put(Ziyaret_Co.c7_Otel_,dir.getOtel());
            satir.put(Ziyaret_Co.c8_Ziyaret_yeri_bilgisi_,dir.getZiyaret_yeri_bilgisi());

            satir.put(Ziyaret_Co.c_Create_date,dir.getCreate_date());
            satir.put(Ziyaret_Co.c_Create_user_id,dir.getCreate_user_id());
            satir.put(Ziyaret_Co.c_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Ziyaret_Co.c_Gunleme_date,dir.getGunleme_date());
            satir.put(Ziyaret_Co.c_Deleted,dir.getDeleted());
            satir.put(Ziyaret_Co.c_mid,dir.getMid());
            satir.put(Ziyaret_Co.c_mustid,dir.getMustid());
        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}


