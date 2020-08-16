package org.pharmacymanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    private String id;

    private Date orderDate;

    @Column(columnDefinition="Decimal(10,2)")
    private Double total;

    private String createBy;

    private Date createDate;

    @OneToMany(mappedBy = "orderEntity",targetEntity = OrderDetailsEntity.class)
    private List<OrderDetailsEntity> orderDetailsEntities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<OrderDetailsEntity> getOrderDetailsEntities() {
        return orderDetailsEntities;
    }

    public void setOrderDetailsEntities(List<OrderDetailsEntity> orderDetailsEntities) {
        this.orderDetailsEntities = orderDetailsEntities;
    }
}
