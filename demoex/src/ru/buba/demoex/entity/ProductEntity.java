package ru.buba.demoex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {

    private int id;
    private String title;
    private String productType;
    private String articleNumber;
    private String image;
    private int productPersonCount;
    private int productionWorkshopNumber;
    private int minCostForAgent;

    public ProductEntity(String title, String productType, String articleNumber, String image, int productPersonCount, int productionWorkshopNumber, int minCostForAgent) {
        this(-1, title, productType, articleNumber, image, productPersonCount, productionWorkshopNumber, minCostForAgent);
    }
}
