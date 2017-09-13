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

import ContractLayer.MediaDoc_Co;
import EntityLayer.MediaDoc;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 8.5.2017.
 */



public class MediaDoc_Data extends DataController<MediaDoc> {

    public MediaDoc_Data(Context ctx) {
        super(ctx,new MediaDoc());
    }



    public List<MediaDoc> loadFromQuery(String queryStr) throws DefaultException {
        List<MediaDoc> list = new ArrayList<MediaDoc>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((MediaDoc) CursorToObject(cursor));
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





    public MediaDoc CursorToObject(Cursor cursor) throws DefaultException {
        MediaDoc dir = new MediaDoc();
        dir.setId(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c0_id)));
        dir.setMedia_adi(cursor.getString(cursor.getColumnIndex(MediaDoc_Co.c2_Media_adi_)));
        dir.setMedia_tipi(cursor.getString(cursor.getColumnIndex(MediaDoc_Co.c3_Media_tipi_)));
        dir.setContent_id(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c4_Content_id_)));
        dir.setDirectory_id(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c5_Directory_id_)));
        dir.setMedia_baslik(cursor.getString(cursor.getColumnIndex(MediaDoc_Co.c6_Media_baslik_)));
        dir.setZiyaret_id(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c7_Ziyaret_id_)));

        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(MediaDoc_Co.c_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(MediaDoc_Co.c_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(MediaDoc_Co.c_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(MediaDoc_Co.c_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(MediaDoc_Co.c_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(MediaDoc_Co.c_mustid)));
        dir.setKaydedildi(cursor.getInt(cursor.getColumnIndex(MediaDoc_Co.c_kaydedildi)));

        return dir;
    }


    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM MEDIA WHERE mid = "+mid);
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
            db.execSQL("DELETE FROM MEDIA WHERE id = "+id);
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




    public Boolean updateFromContent(List<MediaDoc> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MediaDoc kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = MediaDoc_Co.MEDIA_TABLE;
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


    public Boolean insertFromContent(List<MediaDoc> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MediaDoc kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(MediaDoc_Co.MEDIA_TABLE, null, line);
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



    public ContentValues ObjectToContentValues(MediaDoc dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(MediaDoc_Co.c0_id,dir.getId());
            satir.put(MediaDoc_Co.c2_Media_adi_,dir.getMedia_adi());
            satir.put(MediaDoc_Co.c3_Media_tipi_,dir.getMedia_tipi());
            satir.put(MediaDoc_Co.c4_Content_id_,dir.getContent_id());
            satir.put(MediaDoc_Co.c5_Directory_id_,dir.getDirectory_id());
            satir.put(MediaDoc_Co.c6_Media_baslik_,dir.getMedia_baslik());
            satir.put(MediaDoc_Co.c7_Ziyaret_id_,dir.getZiyaret_id());

            satir.put(MediaDoc_Co.c_Create_date,dir.getCreate_date());
            satir.put(MediaDoc_Co.c_Create_user_id,dir.getCreate_user_id());
            satir.put(MediaDoc_Co.c_Gunleyen_id,dir.getGunleyen_id());
            satir.put(MediaDoc_Co.c_Gunleme_date,dir.getGunleme_date());
            satir.put(MediaDoc_Co.c_Deleted,dir.getDeleted());
            satir.put(MediaDoc_Co.c_mid,dir.getMid());
            satir.put(MediaDoc_Co.c_mustid,dir.getMustid());
            satir.put(MediaDoc_Co.c_kaydedildi,dir.getKaydedildi());

        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }



}



