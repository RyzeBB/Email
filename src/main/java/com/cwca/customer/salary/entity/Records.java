package com.cwca.customer.salary.entity;

import lombok.*;

import java.util.List;
@ToString@Setter@Getter@NoArgsConstructor
public class Records {
    private int empTotal=0;
    private int noSendTotal=0;
    private int sendTotal = empTotal-noSendTotal;
    private List<String> noSendList;

    public Records(int empTotal,int noSendTotal,List<String> noSendList){
        this.empTotal = empTotal;
        this.noSendTotal = noSendTotal;
        this.noSendList = noSendList;
    }



}
