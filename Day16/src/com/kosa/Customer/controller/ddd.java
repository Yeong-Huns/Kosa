package com.kosa.Customer.controller;

import com.kosa.Customer.domain.Customer;
import com.kosa.Customer.repository.CustomerJDBCRepository;

import java.util.Arrays;

/**
 * packageName    : com.kosa.account.controller
 * fileName       : ddd
 * author         : Yeong-Huns
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        Yeong-Huns       최초 생성
 */
public class ddd {

    public static void main(String[] args) {
        CustomerJDBCRepository repository = CustomerJDBCRepository.getInstance();

        //repository.Register(new Customer("김영한", 2000));

        //고객등록
        Arrays.asList(
                new Customer("손기정",1000),
                new Customer("바나나",2000),
                new Customer("홍길동",3000),
                new Customer("류호정",4000),
                new Customer("사만다",5000),
                new Customer("이기억",6000),
                new Customer("공혁준",7000)
        ).forEach(repository::Register);

        //전체조회
        repository.customerList().forEach(System.out::println);

        //단건조회
        Customer customer = repository.findById(13).orElseThrow(IllegalArgumentException::new);
        System.out.println(customer);
        //조회한 유저 삭제
        repository.delete(customer);


    }
}
