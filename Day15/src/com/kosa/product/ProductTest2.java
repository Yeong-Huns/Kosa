package com.kosa.product;

import java.util.ArrayList;

/**
 * packageName    : com.kosa.product
 * fileName       : ProductTest2
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
public class ProductTest2 {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        String code = "NAME8";
        String name = "GENGI";

        ProductVO vo = new ProductVO(code, name);
        dao.modProduct(vo);

        ArrayList<ProductVO> list = dao.list(vo);

        for (int i = 0; i < list.size(); i++) {
            ProductVO data = list.get(i);
            String prod_code = data.getProd_code();
            String prod_name = data.getProd_name();
            String prod_color = data.getProd_color();
            int prod_qty = data.getProd_qty();

            System.out.println("아이디는>>"+prod_code+
                    ", 이름은>>"+name+
                    ", 키는>>"+prod_name+
                    ", 체중은>>"+prod_color+
                    ", 나이는>>"+prod_qty);
        }
    }
}
