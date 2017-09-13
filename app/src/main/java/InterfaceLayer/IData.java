package InterfaceLayer;

import java.util.List;

import EntityLayer.BaseEntity;
import ToolLayer.DefaultException;


public interface IData<T extends BaseEntity>
{

    public List<T> list() throws DefaultException;


    public Boolean insert() throws DefaultException;
    public Boolean update() throws DefaultException;
    public Boolean delete() throws DefaultException;
    public T findById(Long id) throws DefaultException;
    public T findByMId(Long id) throws DefaultException;
    public T findByOrgId(Long id) throws DefaultException;
    public List<T> saveChange(List<T> data) throws DefaultException;
    public List<T> loadActiveDataList() throws DefaultException;
    public List<T> loadPassiveDataList() throws DefaultException;
    public List<T> getAllDataFromService(String url) throws DefaultException;
    public Boolean clearDatabaseTable() throws DefaultException;
    public List<T> listFromQuery(StringBuilder sb) throws DefaultException;
    public Boolean reduce() throws DefaultException;
    public Long getRecordCount(String strSql) throws DefaultException;
    public Long getMaxIdOfProDetay(String strSql) throws DefaultException;
    public String getMaxDate(String strSql) throws DefaultException;
}
