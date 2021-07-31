package com.t2012e.reflection.generic;

import com.t2012e.entity.Customer;
import com.t2012e.entity.Student;

public class DemoGeneric {
    public static void main(String[] args) {
//        MasterModel<Customer> model = new MasterModel<>();
//        Customer customer = new Customer();
//        customer.setIdentityNumber("A122");
//        customer.setName("khach hang");
//        customer.setBalance(12.0);
//        customer.setEmail("kiencuong789@gmail.com");
//        model.save(customer);

        MasterModel<Customer> masterModel = new MasterModel<>();
        Customer customer = new Customer();
        masterModel.delete(customer);
    }
}
