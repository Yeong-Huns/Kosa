package com.kosa.Customer.repository;

import com.kosa.Customer.connection.DBConnection;
import com.kosa.Customer.domain.Customer;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.kosa.Customer.repository
 * fileName       : CustomerRepository
 * author         : Yeong-Huns
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        Yeong-Huns       최초 생성
 */
public class CustomerJDBCRepository {
    private static CustomerJDBCRepository instance = null;
    private CustomerJDBCRepository() {}
    public static CustomerJDBCRepository getInstance() {
        if (instance == null) {
            instance = new CustomerJDBCRepository();
        }
        return instance;
    }


    private  Connection conn = DBConnection.getConnection();
    private Statement stmt;
    private ResultSet rs;

    public void Register(Customer customer) {
        String callStatement = "{ call CUSTOMER_PACK.REGISTER(?, ?) }";
        try {
            CallableStatement callableStatement = conn.prepareCall(callStatement);
            callableStatement.setString(1, customer.getName());
            callableStatement.setLong(2, customer.getExpense());
            callableStatement.executeUpdate();
            System.out.println(customer.getName() + "님의 가입요청이 정상 처리 되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<Customer> customerList() {
        List<Customer> customerList = new ArrayList<Customer>();
        String callStatement = "{ call CUSTOMER_PACK.FIND_ALL(?) }";
        try {
            CallableStatement callableStatement = conn.prepareCall(callStatement);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                long expense = rs.getLong(3);
                LocalDate registrationDate = rs.getDate(4).toLocalDate();
                customerList.add(new Customer(id, name, expense, registrationDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return customerList;
    }

    public void UpdateExpense(Customer customer){
        String callStatement = "{ call CUSTOMER_PACK.UPDATE_EXPENSE(?, ?) }";
        try{
            CallableStatement callableStatement = conn.prepareCall(callStatement);
            callableStatement.setLong(1, customer.getId());
            callableStatement.setLong(2, customer.getExpense());
            callableStatement.executeUpdate();
            System.out.println(customer.getName() + "님의 사용금액이 업데이트 되었습니다.");
        }catch(SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
    }
    public void delete(Customer customer){
        String callStatement = "{ call CUSTOMER_PACK.DELETE(?) }";
        try{
            CallableStatement callableStatement = conn.prepareCall(callStatement);
            callableStatement.setLong(1, customer.getId());
            callableStatement.execute();
            System.out.println(customer.getName() + "님의 계정이 정상적으로 삭제되었습니다.");
        }catch(SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
    }
    public Optional<Customer> findById(long RequestId){
        Optional<Customer> customerOptional = Optional.empty();
        String callStatement = "{ call CUSTOMER_PACK.FIND_BY_ID(?, ?) }";
        try{
            CallableStatement callableStatement = conn.prepareCall(callStatement);
            callableStatement.setLong(1, RequestId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            System.out.println("계정 조회 성공.");
            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                long expense = rs.getLong(3);
                LocalDate registrationDate = rs.getDate(4).toLocalDate();
                customerOptional = Optional.of(new Customer(id, name, expense, registrationDate));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
        return customerOptional;
    }
}


/*






    public boolean accountFindOne(String ano) {
        String runSP = "{ ? = call accounts_pack.is_account_exists(?) }";

        boolean isExist = false;

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(runSP);
            callableStatement.registerOutParameter(1, Types.VARCHAR); // 에러
            callableStatement.setString(2, ano);
            callableStatement.executeUpdate();
            String accountExist = callableStatement.getString(1);
            System.out.println(accountExist);
            if (accountExist.equals("true")) {
                isExist = true;
            } else {
                isExist = false;
            }
        } catch (SQLException e) {
            System.out.println("프로시저에서 에러 발생!");
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return isExist;
    }
*/
