package com.kosa.product;

import com.kosa.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * packageName    : com.kosa.product
 * fileName       : ProductDAO
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
public class ProductDAO {
    private Connection conn = DBConnection.getConnection();
    private Statement stmt;
    private ResultSet rs;

    public ArrayList<ProductVO> list() {
        ArrayList<ProductVO> list = new ArrayList<>();
        try {
            String query = "select * from PRODUCT";
            System.out.println(query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String prod_code = rs.getString("PRODUCT_CODE");
                String prod_name = rs.getString("PRODUCT_NAME");
                String prod_color = rs.getString("PRODUCT_COLOR");
                int prod_qty = rs.getInt("PRODUCT_QTY");

                ProductVO data = new ProductVO(prod_code, prod_name, prod_color, prod_qty);

                list.add(data);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //
    public ArrayList<ProductVO> list(ProductVO vo){
        String code = vo.getProd_code();
        String name = vo.getProd_name();
        ArrayList<ProductVO> list = new ArrayList<ProductVO>();
        try {
            String query = "select * from PRODUCT";
            if (code != null && name != null) {
                query += " where prod_code='" + code + "' and prod_name='" + name +"'";
            } else if (code != null && name == null) {
                query += " where prod_code='" + code + "'";
            }
            System.out.println(query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String prod_code = rs.getString("PROD_CODE");
                String prod_name = rs.getString("PROD_NAME");
                String prod_color = rs.getString("PROD_COLOR");
                int prod_qty = rs.getInt("PROD_QTY");

                ProductVO data = new ProductVO(prod_code, prod_name, prod_color, prod_qty);

                list.add(data);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    public void modProduct(ProductVO vo) {
        String prod_code;
        String prod_name;

        prod_code = vo.getProd_code();
        prod_name = vo.getProd_name();

        try {
            String query = "update PRODUCT";
            query += " set prod_name='" + prod_name;
            query += "' where prod_code='" + prod_code + "'";
            System.out.println(query);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
