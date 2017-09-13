package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 21.5.2017.
 */



@Table(name = "DUA")
public class Dua extends  BaseEntity{

    @Override
    public String toString()
    {
        return Dua_adi;
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "Directory_id")
    private Long Directory_id;

    @Column(name = "Dua_adi")
    private String Dua_adi;

    @Column(name = "Dua_voice_name")
    private String Dua_voice_name;

    @Column(name = "Dua_arapca")
    private String Dua_arapca;

    @Column(name = "Dua_okunusu")
    private String Dua_okunusu;

    @Column(name = "Dua_anlami")
    private String Dua_anlami;

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

    @Column(name = "seskaydedildi2")
    private Integer seskaydedildi2;


    @Column(name = "Dua_voice_name2")
    private String Dua_voice_name2;


    public Integer getSeskaydedildi2() {
        return seskaydedildi2;
    }

    public void setSeskaydedildi2(Integer seskaydedildi2) {
        this.seskaydedildi2 = seskaydedildi2;
    }

    public String getDua_voice_name2() {
        return Dua_voice_name2;
    }

    public void setDua_voice_name2(String dua_voice_name2) {
        Dua_voice_name2 = dua_voice_name2;
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

    public String getDua_adi() {
        return Dua_adi;
    }

    public void setDua_adi(String dua_adi) {
        Dua_adi = dua_adi;
    }

    public String getDua_voice_name() {
        return Dua_voice_name;
    }

    public void setDua_voice_name(String dua_voice_name) {
        Dua_voice_name = dua_voice_name;
    }

    public String getDua_arapca() {
        return Dua_arapca;
    }

    public void setDua_arapca(String dua_arapca) {
        Dua_arapca = dua_arapca;
    }

    public String getDua_okunusu() {
        return Dua_okunusu;
    }

    public void setDua_okunusu(String dua_okunusu) {
        Dua_okunusu = dua_okunusu;
    }

    public String getDua_anlami() {
        return Dua_anlami;
    }

    public void setDua_anlami(String dua_anlami) {
        Dua_anlami = dua_anlami;
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

    public Integer getSeskaydedildi() {
        return seskaydedildi;
    }

    public void setSeskaydedildi(Integer seskaydedildi) {
        this.seskaydedildi = seskaydedildi;
    }
}
