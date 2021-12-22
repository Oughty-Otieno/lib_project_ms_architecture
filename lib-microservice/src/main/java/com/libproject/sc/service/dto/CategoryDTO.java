package com.libproject.sc.service.dto;


import com.libproject.sc.domain.Category;

public class CategoryDTO extends Category {

    private Integer total_number;
    private Integer available_number;

    public CategoryDTO(Category category){
        this.setId(category.getId());
        this.setName(category.getName());
        this.setDescription(category.getDescription());
    }

    public Integer getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Integer total_number) {
        this.total_number = total_number;
    }

    public Integer getAvailable_number() {
        return available_number;
    }

    public void setAvailable_number(Integer available_number) {
        this.available_number = available_number;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
            "total_number=" + total_number +
            ", available_number=" + available_number +
            "} " + super.toString();
    }
}
