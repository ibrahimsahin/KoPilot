package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 2.5.2017.
 */

@Table(name = "DIRECTORY")
public class Directory  extends BaseEntity {


    @Override
    public String toString()
    {
        return Directory_adi;
    }

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "Directory_adi", insertable = false, updatable = false)
    private String Directory_adi;

    @Column(name = "Directory_parent_id", insertable = false, updatable = false)
    private Integer Directory_parent_id;

    @Column(name = "Directory_main_menu_id", insertable = false, updatable = false)
    private Integer Directory_main_menu_id;

    @Column(name = "Directory_level", insertable = false, updatable = false)
    private Integer Directory_level;

    @Column(name = "Create_date", insertable = false, updatable = false)
    private String Create_date;

    @Column(name = "Create_user_id", insertable = false, updatable = false)
    private Integer Create_user_id;

    @Column(name = "Gunleyen_id", insertable = false, updatable = false)
    private Integer Gunleyen_id;

    @Column(name = "Gunleme_date", insertable = false, updatable = false)
    private String Gunleme_date;

    @Column(name = "Deleted", insertable = false, updatable = false)
    private Integer Deleted;

    @Column(name = "Language_id", insertable = false, updatable = false)
    private Long Language_id;


    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    public Long getLanguage_id() {
        return Language_id;
    }

    public void setLanguage_id(Long language_id) {
        Language_id = language_id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDirectory_adi() {
        return Directory_adi;
    }

    public void setDirectory_adi(String directory_adi) {
        Directory_adi = directory_adi;
    }

    public Integer getDirectory_parent_id() {
        return Directory_parent_id;
    }

    public void setDirectory_parent_id(Integer directory_parent_id) {
        Directory_parent_id = directory_parent_id;
    }

    public Integer getDirectory_main_menu_id() {
        return Directory_main_menu_id;
    }

    public void setDirectory_main_menu_id(Integer directory_main_menu_id) {
        Directory_main_menu_id = directory_main_menu_id;
    }

    public Integer getDirectory_level() {
        return Directory_level;
    }

    public void setDirectory_level(Integer directory_level) {
        Directory_level = directory_level;
    }

    public String getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(String create_date) {
        Create_date = create_date;
    }

    public Integer getCreate_user_id() {
        return Create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        Create_user_id = create_user_id;
    }

    public Integer getGunleyen_id() {
        return Gunleyen_id;
    }

    public void setGunleyen_id(Integer gunleyen_id) {
        Gunleyen_id = gunleyen_id;
    }

    public String getGunleme_date() {
        return Gunleme_date;
    }

    public void setGunleme_date(String gunleme_date) {
        Gunleme_date = gunleme_date;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    @Override
    public Long getMid() {
        return mid;
    }

    @Override
    public void setMid(Long mid) {
        this.mid = mid;
    }

    @Override
    public Long getMustid() {
        return mustid;
    }

    @Override
    public void setMustid(Long mustid) {
        this.mustid = mustid;
    }
}
