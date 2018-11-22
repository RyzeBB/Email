package com.cwca.customer.salary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data@NoArgsConstructor@ToString

public class EmpInfo {
    private Integer id;
    private String empcode;
    private String empname;
    private String departname;
    private String email;

    public EmpInfo(String empcode,String empname,String departname,String email){
        this.empcode = empcode;
        this.empname = empname;
        this.departname = departname;
        this.email = email;
    }

}
