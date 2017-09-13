package EntityLayer;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 15.8.2017.
 */

@Table(name = "NOKTA")
public class Nokta extends  BaseEntity {

    @Override
    public String toString() {
        return String.valueOf(mid);
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "Nokta_adi")
    private String Nokta_adi;

    @Column(name = "Longtitude")
    private Double Longtitude;

    @Column(name = "Latitude")
    private Double Latitude;


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

    public String getNokta_adi() {
        return Nokta_adi;
    }

    public void setNokta_adi(String nokta_adi) {
        Nokta_adi = nokta_adi;
    }

    public Double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(Double longtitude) {
        Longtitude = longtitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
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
