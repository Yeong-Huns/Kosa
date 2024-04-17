package com.kosa.product;

import lombok.Getter;

/**
 * packageName    : com.kosa.product
 * fileName       : ProductVO
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
@Getter
public class ProductVO {

    private String prod_code;
    private String prod_name;
    private String prod_color;
    private int prod_qty;

    public ProductVO(String prod_code, String prod_name) {
        this.prod_code = prod_code;
        this.prod_name = prod_name;
    }

    public ProductVO(String prod_code, String prod_name, String prod_color, int prod_qty) {
        this.prod_code = prod_code;
        this.prod_name = prod_name;
        this.prod_color = prod_color;
        this.prod_qty = prod_qty;
    }
}
