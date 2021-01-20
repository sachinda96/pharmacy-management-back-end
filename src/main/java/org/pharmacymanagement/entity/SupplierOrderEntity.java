package org.pharmacymanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SupplierOrderEntity {

    @Id
    private String id;
    private Date orderDate;
    @Column(columnDefinition="Decimal(10,2)")
    private Double total;

    @ManyToOne(cascade = CascadeType.ALL)
    private SupplierEntity supplierEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public SupplierEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }
}
