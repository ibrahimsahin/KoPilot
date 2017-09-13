package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 8.5.2017.
 */

@Table(name = "MEDIA")
public class MediaDoc extends BaseEntity{

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "Media_adi" , insertable = true)
    private String Media_adi;

    @Column(name = "Media_tipi" , insertable = true)
    private String Media_tipi;


    @Column(name = "Content_id" , insertable = true)
    private Long Content_id;


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


    @Column(name = "kaydedildi")
    private Integer kaydedildi;

    @Column(name = "Directory_id")
    private Long Directory_id;

    @Column(name = "Media_baslik")
    private String Media_baslik;

    @Column(name = "Ziyaret_id")
    private Long Ziyaret_id;

    public Long getZiyaret_id() {
        return Ziyaret_id;
    }

    public void setZiyaret_id(Long ziyaret_id) {
        Ziyaret_id = ziyaret_id;
    }

    public String getMedia_baslik() {
        return Media_baslik;
    }

    public void setMedia_baslik(String media_baslik) {
        Media_baslik = media_baslik;
    }

    public Long getDirectory_id() {
        return Directory_id;
    }

    public void setDirectory_id(Long directory_id) {
        Directory_id = directory_id;
    }

    public Integer getKaydedildi() {
        return kaydedildi;
    }

    public void setKaydedildi(Integer kaydedildi) {
        this.kaydedildi = kaydedildi;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMedia_adi() {
        return Media_adi;
    }

    public void setMedia_adi(String media_adi) {
        Media_adi = media_adi;
    }

    public String getMedia_tipi() {
        return Media_tipi;
    }

    public void setMedia_tipi(String media_tipi) {
        Media_tipi = media_tipi;
    }

    public Long getContent_id() {
        return Content_id;
    }

    public void setContent_id(Long content_id) {
        Content_id = content_id;
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
