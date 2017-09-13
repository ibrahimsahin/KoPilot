package DataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import EntityLayer.BaseEntity;
import InterfaceLayer.IData;
import ToolLayer.DefaultException;
import ToolLayer.SqlBuilder;
import ankarabt.kopilot.DbHelper;


public abstract class DataController<T extends BaseEntity> implements IData<T> {

    public DataController(Context ctx, T _entity) {
        this.setDataList(new ArrayList<T>());
        helper = DbHelper.getInstance(ctx);
        entity = _entity;
    }

    private ArrayList<T> dataList;
    public Context context;
    public SQLiteDatabase db;
    public DbHelper helper;
    public SqlBuilder<T> builder;
    public T entity;

    @Override
    public List<T> saveChange(List<T> data) throws DefaultException {
        for (T item : data)
        {
            if (item.getMid()!=null &&  item.getMid()>0)
            {
                //UPDATE
                dataList = new ArrayList<T>();
                dataList.add(item);
                Boolean sta= update();
                if (!sta)
                    throw new DefaultException("Hata:DataController->SaveChange to Update\niÅŸlem gerÃ§ekleÅŸtirilemedi !");
                else
                    item = dataList.get(0);
            }
            else
            {
                // INSERT
                dataList = new ArrayList<T>();
                dataList.add(item);
                Boolean sta=  insert();
                if (!sta)
                    throw new DefaultException("Hata:DataController->SaveChange\niÅŸlem gerÃ§ekleÅŸtirilemedi !");
                else
                    item = dataList.get(0);

            }
        }
        return data;
    }

    public ArrayList<T> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public T findById(Long id) throws DefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();

            String query = builder.selectQueryFindById(id);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu baÅŸarÄ±sÄ±z kayÄ±t bulunamadÄ±!");

            }
        } catch (DefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
            return dlist.get(0);
        else
            return null;
    }

    @Override
    public T findByMId(Long mid) throws DefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String query = builder.selectQueryFindByMId(mid);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu baÅŸarÄ±sÄ±z kayÄ±t bulunamadÄ±!");

            }
        } catch (DefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
            return dlist.get(0);
        else
            return null;
    }

    @Override
    public T findByOrgId(Long orgid) throws DefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String query = builder.selectQueryFindByOrgId(orgid);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu baÅŸarÄ±sÄ±z kayÄ±t bulunamadÄ±!");

            }
        } catch (DefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
            return dlist.get(0);
        else
            return null;
    }

    @Override
    public Boolean insert() throws DefaultException {
        Long m_id = null;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList())
                {
                    long id = 0;
                    try {
                        ContentValues line = new ContentValues();
                        line = builder.createContentValuesFromEntity(kayit);
                        line.remove("mid");
                        String tableName = builder.getTableName();
                        m_id = db.insertOrThrow(tableName, null, line);
                        if (m_id > 0) {
                            status = true;
                            kayit.setMid(m_id);
                            Log.d("DataController", "Kayit eklendi mid:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "KayÄ±t ekleme baÅŸarÄ±sÄ±z !");
                            status = false;
                            throw new DefaultException("DataController-insert:Kayit Eklenemedi, database tablosu hatalÄ± !" + kayit.toString());
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
            } catch (DefaultException e) {
                e.printStackTrace();
                Log.d("DataController", e.getMessage());
                status = false;
                throw e;
            }

        } else {

            status = false;
            throw new DefaultException("DataController(insert)-Eklenecek kayÄ±t bulunamadÄ± ! Liste boÅŸ...");
        }

        return status;
    }

    @Override
    public ArrayList<T> list() throws DefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String tableNAME = builder.getTableName();
            Cursor cursor = db.rawQuery("SELECT * FROM  " + builder.getTableName(), null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            }
        } catch (DefaultException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());

        } finally {
            db.close();
            return getDataList();
        }
    }

    @Override
    public Boolean update() throws DefaultException {
        int m_id = 0;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList()) {
                    long id = 0;
                    try {
                        ContentValues line = new ContentValues();

                        line = builder.createContentValuesFromEntity(kayit);

                        String tableName = builder.getTableName();
                        Log.v("tabe name","_>"+tableName);
                        String[] whereParameters = {String.valueOf(kayit.getMid())};
                        String WhereArgs = "mid=?";
                        m_id = db.update(tableName, line, WhereArgs, whereParameters);

                        if (m_id > 0) {
                            status = true;
                            Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "KayÄ±t guncelleme baÅŸarÄ±sÄ±z !");
                            status = false;
                            throw new DefaultException("DataController-update:Kayit guncellenemedi !" + kayit.toString());
                        }


                    } catch (SQLiteConstraintException e) {
                        status = false;
                        Log.d("DataController", e.getMessage());
                        throw new DefaultException("DataController(update)Hata:" + e.getMessage());
                    } catch (SQLiteDatatypeMismatchException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new DefaultException("DataController(update)Hata:" + e.getMessage());
                    } catch (SQLiteException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new DefaultException("DataController(update)Hata:" + e.getMessage());
                    }


                }

            } catch (DefaultException e) {
                e.printStackTrace();
                Log.d("DataController", e.getMessage());
                status = false;
                throw e;
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            if(db.isOpen())
                db.close();

        } else {

            status = false;
            throw new DefaultException("DataController(update)-Guncellenecek kayÄ±t bulunamadÄ± ! Liste boÅŸ...");
        }


        return status;
    }

    @Override
    public Boolean delete() throws DefaultException {
        int m_id = 0;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList()) {
                    long id = 0;
                    try {
                        String[] whereParameters = {String.valueOf(kayit.getMid())};
                        String WhereArgs = "mid=?";
                        String tableName = builder.getTableName();
                        m_id = db.delete(tableName, WhereArgs, whereParameters);

                        if (m_id > 0) {
                            status = true;
                            Log.d("DataController", "Kayit silindi id:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "KayÄ±t silme baÅŸarÄ±sÄ±z !");
                            status = false;
                            throw new DefaultException("DataController-delete:Kayit silinemedi, database tablosu hatalÄ± !" + kayit.toString()+kayit.getMid());
                        }


                    } catch (SQLiteConstraintException e) {
                        status = false;
                        Log.d("DataController", e.getMessage());
                        throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
                    } catch (SQLiteDatatypeMismatchException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
                    } catch (SQLiteException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
                    }


                }
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            } catch (DefaultException e) {
                e.printStackTrace();
                Log.d("DataController", "DataController(delete):" + e.getMessage());
                status = false;
                throw e;
            }

        } else {

            status = false;
            throw new DefaultException("DataController(delete)-Silinecek kayÄ±t bulunamadÄ± ! Liste boÅŸ...");
        }

        return status;
    }

    @Override
    public Boolean clearDatabaseTable() throws DefaultException {
        Boolean status = true;
        try {
            builder = new SqlBuilder<T>(entity);
            String tableName = builder.getTableName();
            db = helper.getWritableDatabase();
            db.beginTransaction();
            int deletedcount = 0;
//            deletedcount = db.delete(tableName, "1", null);
            db.execSQL("DELETE FROM " + builder.getTableName());
            Log.d(tableName, builder.getTableName()+" tablosunda  kayÄ±tlar silindi.ID=>"+entity.getId());
        } catch (DefaultException e) {
            status = false;
            e.printStackTrace();
            throw e;
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







    public T createNewEntity(T entity) throws DefaultException {
        T ent = null;
        try {
            Constructor[] ctors = entity.getClass().getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
            ctor.setAccessible(true);
            ent = (T) ctor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new DefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new DefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new DefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException("DataController:createNewEntity->" + e.getMessage());
        }
        return ent;
    }

    @Override
    public List<T> loadPassiveDataList() throws DefaultException {

        return null;
    }

    @Override
    public List<T> loadActiveDataList() throws DefaultException {
        return null;
    }

    @Override
    public List<T> getAllDataFromService(String url) throws DefaultException {

        return null;
    }

    @Override
    public List<T> listFromQuery(StringBuilder sb) throws DefaultException {
        dataList = new ArrayList<>();
        if (sb != null && sb.toString().trim().length() > 0) {

            try {
                getDataList().clear();
                db = helper.getReadableDatabase();
                Cursor crsr = db.rawQuery(sb.toString(), null);
                builder = new SqlBuilder<T>(entity);
                dataList.clear();
                if (crsr.getCount() > 0) {
                    dataList = (ArrayList<T>) builder.readFromCursor(crsr);
                    crsr.close();
                }
            }
            catch (DefaultException e) {
                Log.d("DataController:list", e.getMessage());
                throw new DefaultException("DataController:list->" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController:list", e.getMessage());
                throw new DefaultException("DataController:list->" + e.getMessage());
            } catch (Exception e) {
                Log.d("DataController:list", e.getMessage());
                throw new DefaultException("DataController:list->" + e.getMessage());
            } catch (Throwable e) {
                Log.d("DataController:list", e.getMessage());
                throw new DefaultException("DataController:list->" + e.getMessage());
            }
            db.close();
        }
        return getDataList();
    }

    @Override
    public Boolean reduce() throws DefaultException {
        int m_id = 0;
        Boolean status = true;
        try {
            builder = new SqlBuilder<T>(entity);
            db = helper.getWritableDatabase();
            db.beginTransaction();
            long id = 0;
            try {
                String[] whereParameters = {String.valueOf(100)};
                String WhereArgs = "id>=?";
                String tableName = builder.getTableName();
                m_id = db.delete(tableName, WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.d("DataController", "Kayit silindi id:" + m_id  );
                } else {
                    Log.d("DataController", "KayÄ±t silme baÅŸarÄ±sÄ±z !");
                    status = false;
                    throw new DefaultException("DataController-insert:Kayit silinemedi, database tablosu hatalÄ± !" );
                }


            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(delete)Hata:" + e.getMessage());
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        } catch (DefaultException e) {
            e.printStackTrace();
            Log.d("DataController", "DataController(delete):" + e.getMessage());
            status = false;
            throw e;
        }

        return status;
    }

    @Override
    public Long getRecordCount(String strSql) throws DefaultException {
        Long count_=0L;
        try {
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String tableNAME = builder.getTableName();
            Cursor cursor = db.rawQuery(strSql, null);
            if (cursor.getCount() > 0) {
                count_=(long)cursor.getCount();
            }
        } catch (DefaultException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new DefaultException("DataController:list->" + e.getMessage());

        } finally {
            db.close();
            return count_;
        }
    }


    @Override
    public Long getMaxIdOfProDetay(String strSql) throws DefaultException {

        Long max_= -1L;
        try {

            if(db == null)
                Log.v("db","null");
            else {
                if (db.isOpen())
                    Log.v("db", "opened");
                if (db.isDbLockedByCurrentThread())
                    Log.v("db", "lockedbycureetnthread");
            }
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(strSql, null);
            if(cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                max_= cursor.getLong(cursor.getColumnIndex("max_"));
                cursor.close();
                Log.v("getmaxFunction","if");
            }
            else
            {
                Log.v("getmaxFunction","else");
            }

        }catch (Exception e){
            Log.v("getMaxId","throw exception");
            throw new DefaultException(e.toString());
        }
        finally {
            db.close();
            return max_;
        }
    }



    @Override
    public String getMaxDate(String strSql) throws DefaultException {

        String max_= "-1";
        try {
            if(db == null)
                Log.v("db","null");
            else {
                if (db.isOpen())
                    Log.v("db", "opened");
                if (db.isDbLockedByCurrentThread())
                    Log.v("db", "lockedbycureetnthread");
            }
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(strSql, null);
            if(cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                max_= cursor.getString(cursor.getColumnIndex("max_"));
                cursor.close();
                Log.v("getmaxFunction","if");
            }
            else
            {
                Log.v("getmaxFunction","else");
            }

        }catch (Exception e){
            Log.v("getMaxId","throw exception");
            throw new DefaultException(e.toString());
        }
        finally {
            db.close();
            return max_;
        }
    }



}


