package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 28.5.2017.
 */

@Table(name = "ZIYARET")
public class Ziyaret  extends  BaseEntity{

    @Override
    public String toString()
    {
        return Ziyaret_yer_adi;
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "Ziyaret_yer_adi")
    private String Ziyaret_yer_adi;

    @Column(name = "Directory_id")
    private Long Directory_id;

    @Column(name = "Language_id")
    private Long Language_id;

    @Column(name = "X_koor")
    private String X_koor;

    @Column(name = "Y_koor")
    private String Y_koor;

    @Column(name = "Ziyaret_yeri_bilgisi")
    private String Ziyaret_yeri_bilgisi;


    @Column(name = "Otel")
    private Integer Otel;

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


    public String getZiyaret_yeri_bilgisi() {
        return Ziyaret_yeri_bilgisi;
    }

    public void setZiyaret_yeri_bilgisi(String ziyaret_yeri_bilgisi) {
        Ziyaret_yeri_bilgisi = ziyaret_yeri_bilgisi;
    }

    public Integer getOtel() {
        return Otel;
    }

    public void setOtel(Integer otel) {
        Otel = otel;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getZiyaret_yer_adi() {
        return Ziyaret_yer_adi;
    }

    public void setZiyaret_yer_adi(String ziyaret_yer_adi) {
        Ziyaret_yer_adi = ziyaret_yer_adi;
    }

    public Long getDirectory_id() {
        return Directory_id;
    }

    public void setDirectory_id(Long directory_id) {
        Directory_id = directory_id;
    }

    public Long getLanguage_id() {
        return Language_id;
    }

    public void setLanguage_id(Long language_id) {
        Language_id = language_id;
    }

    public String getX_koor() {
        return X_koor;
    }

    public void setX_koor(String x_koor) {
        X_koor = x_koor;
    }

    public String getY_koor() {
        return Y_koor;
    }

    public void setY_koor(String y_koor) {
        Y_koor = y_koor;
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
