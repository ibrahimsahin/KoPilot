package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 26.5.2017.
 */

@Table(name = "UYGULAMALAR")
public class Uygulamalar extends  BaseEntity{

    @Override
    public String toString()
    {
        return Uygulama_adi;
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "Uygulama_adi")
    private String Uygulama_adi;

    @Column(name = "Uygulama_logo")
    private String Uygulama_logo;

    @Column(name = "Uygulama_linki_android")
    private String Uygulama_linki_android;

    @Column(name = "Uygulama_linki_ios")
    private String Uygulama_linki_ios;


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

    @Column(name = "imagekaydedildi")
    private Integer imagekaydedildi;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUygulama_adi() {
        return Uygulama_adi;
    }

    public void setUygulama_adi(String uygulama_adi) {
        Uygulama_adi = uygulama_adi;
    }

    public String getUygulama_logo() {
        return Uygulama_logo;
    }

    public void setUygulama_logo(String uygulama_logo) {
        Uygulama_logo = uygulama_logo;
    }

    public String getUygulama_linki_android() {
        return Uygulama_linki_android;
    }

    public void setUygulama_linki_android(String uygulama_linki_android) {
        Uygulama_linki_android = uygulama_linki_android;
    }

    public String getUygulama_linki_ios() {
        return Uygulama_linki_ios;
    }

    public void setUygulama_linki_ios(String uygulama_linki_ios) {
        Uygulama_linki_ios = uygulama_linki_ios;
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

    public Integer getImagekaydedildi() {
        return imagekaydedildi;
    }

    public void setImagekaydedildi(Integer imagekaydedildi) {
        this.imagekaydedildi = imagekaydedildi;
    }
}
