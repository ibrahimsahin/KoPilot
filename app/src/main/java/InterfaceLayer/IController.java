package InterfaceLayer;

import java.util.List;

import EntityLayer.BaseEntity;
import ToolLayer.DefaultException;


public interface IController<T extends BaseEntity> {


    public void sync(T ent)throws DefaultException;
    public void edit(T ent)throws DefaultException;
    public Boolean create(T ent)throws DefaultException;
    public void delete(T ent)throws DefaultException;
    public T getModelByMid(Long mId)throws DefaultException;
    public T getModelById(Long id_)throws DefaultException;
    public void setModel(T ent)throws DefaultException;
    public List<T> getModelList()throws DefaultException;
    public void  setModelList(List<T> mdllist)throws DefaultException;

}
