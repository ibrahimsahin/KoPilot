package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 11.7.2017.
 */

@Table(name = "GUZERGAH")
public class Guzergah  extends  BaseEntity{

    @Override
    public String toString()
    {
        return Guzergah_adi;
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "Guzergah_adi")
    private String Guzergah_adi;

    @Column(name = "Baslangic_adi")
    private String Baslangic_adi;

    @Column(name = "Bitis_adi")
    private String Bitis_adi;


    @Column(name = "Baslangic_koordinat")
    private String Baslangic_koordinat;

    @Column(name = "Bitis_koordinat")
    private String Bitis_koordinat;

    @Column(name = "Mesafe")
    private Double Mesafe;

    @Column(name = "Azami_hiz")
    private Double Azami_hiz;

    @Column(name = "Azami_sure")
    private Double Azami_sure;

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


    public String getGuzergah_adi() {
        return Guzergah_adi;
    }

    public void setGuzergah_adi(String guzergah_adi) {
        Guzergah_adi = guzergah_adi;
    }

    public String getBaslangic_adi() {
        return Baslangic_adi;
    }

    public void setBaslangic_adi(String baslangic_adi) {
        Baslangic_adi = baslangic_adi;
    }

    public String getBitis_adi() {
        return Bitis_adi;
    }

    public void setBitis_adi(String bitis_adi) {
        Bitis_adi = bitis_adi;
    }

    public String getBaslangic_koordinat() {
        return Baslangic_koordinat;
    }

    public void setBaslangic_koordinat(String baslangic_koordinat) {
        Baslangic_koordinat = baslangic_koordinat;
    }

    public String getBitis_koordinat() {
        return Bitis_koordinat;
    }

    public void setBitis_koordinat(String bitis_koordinat) {
        Bitis_koordinat = bitis_koordinat;
    }

    public Double getMesafe() {
        return Mesafe;
    }

    public void setMesafe(Double mesafe) {
        Mesafe = mesafe;
    }

    public Double getAzami_hiz() {
        return Azami_hiz;
    }

    public void setAzami_hiz(Double azami_hiz) {
        Azami_hiz = azami_hiz;
    }

    public Double getAzami_sure() {
        return Azami_sure;
    }

    public void setAzami_sure(Double azami_sure) {
        Azami_sure = azami_sure;
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
