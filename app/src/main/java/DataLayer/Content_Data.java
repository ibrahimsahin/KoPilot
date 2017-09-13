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

import ContractLayer.Content_Co;
import EntityLayer.Content;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 8.5.2017.
 */

public class Content_Data extends DataController<Content> {

    public Content_Data(Context ctx) {
        super(ctx,new Content());
    }



    public List<Content> loadFromQuery(String queryStr) throws DefaultException {
        List<Content> list = new ArrayList<Content>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Content) CursorToObject(cursor));
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





    public Content CursorToObject(Cursor cursor) throws DefaultException {
        Content dir = new Content();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Content_Co.c0_id)));
        dir.setDirectory_id(cursor.getLong(cursor.getColumnIndex(Content_Co.c2_Directory_id_)));
        dir.setContent_baslik(cursor.getString(cursor.getColumnIndex(Content_Co.c3_Content_baslik_)));
        dir.setContent_metin(cursor.getString(cursor.getColumnIndex(Content_Co.c4_Content_metin_)));
        dir.setContent_banner_media_type(cursor.getString(cursor.getColumnIndex(Content_Co.c5_Content_banner_media_type_)));
        dir.setContent_voice_name(cursor.getString(cursor.getColumnIndex(Content_Co.c6_Content_voice_name_)));
        dir.setContent_gecerli_son_tarih(cursor.getString(cursor.getColumnIndex(Content_Co.c7_Content_gecerli_son_tarih_)));
        dir.setContent_dokuman_type(cursor.getString(cursor.getColumnIndex(Content_Co.c8_Content_dokuman_type_)));
        dir.setLanguage_id(cursor.getLong(cursor.getColumnIndex(Content_Co.c9_Language_id_)));

        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Content_Co.c_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Content_Co.c_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Content_Co.c_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Content_Co.c_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Content_Co.c_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Content_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Content_Co.c_mustid)));

        dir.setSeskaydedildi(cursor.getInt(cursor.getColumnIndex(Content_Co.c_seskaydedildi)));

        return dir;
    }




    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM CONTENT WHERE mid = "+mid);
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
            db.execSQL("DELETE FROM CONTENT WHERE id = "+id);
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




    public Boolean updateFromContent(List<Content> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Content kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Content_Co.CONTENT_TABLE;
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



    public Boolean insertFromContent(List<Content> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Content kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Content_Co.CONTENT_TABLE, null, line);
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

    public ContentValues ObjectToContentValues(Content dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Content_Co.c0_id,dir.getId());
            satir.put(Content_Co.c2_Directory_id_,dir.getDirectory_id());
            satir.put(Content_Co.c3_Content_baslik_,dir.getContent_baslik());
            satir.put(Content_Co.c4_Content_metin_,dir.getContent_metin());
            satir.put(Content_Co.c5_Content_banner_media_type_,dir.getContent_banner_media_type());
            satir.put(Content_Co.c6_Content_voice_name_,dir.getContent_voice_name());
            satir.put(Content_Co.c7_Content_gecerli_son_tarih_,dir.getContent_gecerli_son_tarih());
            satir.put(Content_Co.c8_Content_dokuman_type_,dir.getContent_dokuman_type());
            satir.put(Content_Co.c9_Language_id_,dir.getLanguage_id());

            satir.put(Content_Co.c_Create_date,dir.getCreate_date());
            satir.put(Content_Co.c_Create_user_id,dir.getCreate_user_id());
            satir.put(Content_Co.c_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Content_Co.c_Gunleme_date,dir.getGunleme_date());
            satir.put(Content_Co.c_Deleted,dir.getDeleted());
            satir.put(Content_Co.c_mid,dir.getMid());
            satir.put(Content_Co.c_mustid,dir.getMustid());
            satir.put(Content_Co.c_seskaydedildi,dir.getSeskaydedildi());

        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}


