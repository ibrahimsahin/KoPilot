package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 8.5.2017.
 */

@Table(name = "LANGUAGE")
public class Language extends BaseEntity {

    @Override
    public String toString()
    {
        return Language_adi;
    }

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "Language_adi", insertable = false, updatable = false)
    private String Language_adi;

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

    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage_adi() {
        return Language_adi;
    }

    public void setLanguage_adi(String language_adi) {
        Language_adi = language_adi;
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
