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

import ContractLayer.Uygulamalar_Co;
import EntityLayer.Uygulamalar;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 21.5.2017.
 */

public class Uygulamalar_Data extends DataController<Uygulamalar> {

    public Uygulamalar_Data(Context ctx) {
        super(ctx,new Uygulamalar());
    }



    public List<Uygulamalar> loadFromQuery(String queryStr) throws DefaultException {
        List<Uygulamalar> list = new ArrayList<Uygulamalar>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Uygulamalar) CursorToObject(cursor));
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





    public Uygulamalar CursorToObject(Cursor cursor) throws DefaultException {
        Uygulamalar dir = new Uygulamalar();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Uygulamalar_Co.c0_id)));
        dir.setUygulama_adi(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c2_Uygulama_adi_)));
        dir.setUygulama_logo(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c3_Uygulama_logo_)));
        dir.setUygulama_linki_android(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c4_Uygulama_linki_android_)));
        dir.setUygulama_linki_ios(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c5_Uygulama_linki_ios_)));

        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Uygulamalar_Co.c_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Uygulamalar_Co.c_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Uygulamalar_Co.c_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Uygulamalar_Co.c_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Uygulamalar_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Uygulamalar_Co.c_mustid)));

        dir.setImagekaydedildi(cursor.getInt(cursor.getColumnIndex(Uygulamalar_Co.c_imagekaydedildi)));

        return dir;
    }




    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM UYGULAMALAR WHERE mid = "+mid);
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
            db.execSQL("DELETE FROM UYGULAMALAR WHERE id = "+id);
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




    public Boolean updateFromContent(List<Uygulamalar> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Uygulamalar kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Uygulamalar_Co.UYGULAMALAR_TABLE;
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



    public Boolean insertFromContent(List<Uygulamalar> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Uygulamalar kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Uygulamalar_Co.UYGULAMALAR_TABLE, null, line);
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

    public ContentValues ObjectToContentValues(Uygulamalar dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Uygulamalar_Co.c0_id,dir.getId());
            satir.put(Uygulamalar_Co.c2_Uygulama_adi_,dir.getUygulama_adi());
            satir.put(Uygulamalar_Co.c3_Uygulama_logo_,dir.getUygulama_logo());
            satir.put(Uygulamalar_Co.c4_Uygulama_linki_android_,dir.getUygulama_linki_android());
            satir.put(Uygulamalar_Co.c5_Uygulama_linki_ios_,dir.getUygulama_linki_ios());


            satir.put(Uygulamalar_Co.c_Create_date,dir.getCreate_date());
            satir.put(Uygulamalar_Co.c_Create_user_id,dir.getCreate_user_id());
            satir.put(Uygulamalar_Co.c_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Uygulamalar_Co.c_Gunleme_date,dir.getGunleme_date());
            satir.put(Uygulamalar_Co.c_Deleted,dir.getDeleted());
            satir.put(Uygulamalar_Co.c_mid,dir.getMid());
            satir.put(Uygulamalar_Co.c_mustid,dir.getMustid());
            satir.put(Uygulamalar_Co.c_imagekaydedildi,dir.getImagekaydedildi());

        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}


