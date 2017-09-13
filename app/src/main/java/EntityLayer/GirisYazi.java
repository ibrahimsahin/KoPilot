package EntityLayer;

import AnnotationLayer.Column;

/**
 * Created by isahin on 30.5.2017.
 */

public class GirisYazi {

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "Giris_baslik", insertable = false, updatable = false)
    private String Giris_baslik;

    @Column(name = "Giris_metin", insertable = false, updatable = false)
    private String Giris_metin;

    public String getGiris_baslik() {
        return Giris_baslik;
    }

    public void setGiris_baslik(String giris_baslik) {
        Giris_baslik = giris_baslik;
    }

    public String getGiris_metin() {
        return Giris_metin;
    }

    public void setGiris_metin(String giris_metin) {
        Giris_metin = giris_metin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
