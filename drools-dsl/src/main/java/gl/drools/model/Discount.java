package gl.drools.model;

import java.util.Date;

public class Discount {
    private Double value;
    private Date validUntil;

    public Discount(Double value, Date validUntil) {
        this.value = value;
        this.validUntil = validUntil;
    }

    public Discount(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
