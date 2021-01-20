package org.pharmacymanagement.dto;

import java.util.List;

public class SupplierOrderDto {

    private SupplierDto supplier;
    private List<ItemDto> itemList;

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }

    public List<ItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDto> itemList) {
        this.itemList = itemList;
    }
}
