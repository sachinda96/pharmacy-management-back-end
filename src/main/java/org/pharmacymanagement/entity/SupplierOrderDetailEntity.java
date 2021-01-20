package org.pharmacymanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SupplierOrderDetailEntity {

    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ItemEntity itemEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private SupplierOrderEntity supplierOrderEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public SupplierOrderEntity getSupplierOrderEntity() {
        return supplierOrderEntity;
    }

    public void setSupplierOrderEntity(SupplierOrderEntity supplierOrderEntity) {
        this.supplierOrderEntity = supplierOrderEntity;
    }
}
