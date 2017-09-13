package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 8.5.2017.
 */

@Table(name = "CONTENT")
public class Content  extends BaseEntity {

    @Override
    public String toString()
    {
        return Content_baslik;
    }

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "Directory_id", insertable = false, updatable = false)
    private Long Directory_id;


    @Column(name = "Content_baslik", insertable = false, updatable = false)
    private String Content_baslik;


    @Column(name = "Content_metin", insertable = false, updatable = false)
    private String Content_metin;


    @Column(name = "Content_banner_media_type", insertable = false, updatable = false)
    private String Content_banner_media_type;


    @Column(name = "Content_voice_name", insertable = false, updatable = false)
    private String Content_voice_name;


    @Column(name = "Content_gecerli_son_tarih", insertable = false, updatable = false)
    private String Content_gecerli_son_tarih;


    @Column(name = "Content_dokuman_type", insertable = false, updatable = false)
    private String Content_dokuman_type;


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


    @Column(name = "seskaydedildi")
    private Integer seskaydedildi;

    public Integer getSeskaydedildi() {
        return seskaydedildi;
    }

    public void setSeskaydedildi(Integer seskaydedildi) {
        this.seskaydedildi = seskaydedildi;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDirectory_id() {
        return Directory_id;
    }

    public void setDirectory_id(Long directory_id) {
        Directory_id = directory_id;
    }

    public String getContent_baslik() {
        return Content_baslik;
    }

    public void setContent_baslik(String content_baslik) {
        Content_baslik = content_baslik;
    }

    public String getContent_metin() {
        return Content_metin;
    }

    public void setContent_metin(String content_metin) {
        Content_metin = content_metin;
    }

    public String getContent_banner_media_type() {
        return Content_banner_media_type;
    }

    public void setContent_banner_media_type(String content_banner_media_type) {
        Content_banner_media_type = content_banner_media_type;
    }

    public String getContent_voice_name() {
        return Content_voice_name;
    }

    public void setContent_voice_name(String content_voice_name) {
        Content_voice_name = content_voice_name;
    }

    public String getContent_gecerli_son_tarih() {
        return Content_gecerli_son_tarih;
    }

    public void setContent_gecerli_son_tarih(String content_gecerli_son_tarih) {
        Content_gecerli_son_tarih = content_gecerli_son_tarih;
    }

    public String getContent_dokuman_type() {
        return Content_dokuman_type;
    }

    public void setContent_dokuman_type(String content_dokuman_type) {
        Content_dokuman_type = content_dokuman_type;
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

    public Long getLanguage_id() {
        return Language_id;
    }

    public void setLanguage_id(Long language_id) {
        Language_id = language_id;
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
