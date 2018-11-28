package com.cwca.customer.salary.entity;

import lombok.*;

import java.util.List;
import java.util.Map;

@ToString@Setter@Getter@NoArgsConstructor
public class Records {
    private int empTotal;
    private int noSendTotal;
    private int sendTotal;
    private List<String> noSendList;
    private List<String> failReason;

    public Records(int empTotal,int noSendTotal,List<String> noSendList){
        this.empTotal = empTotal;
        this.noSendTotal = noSendTotal;
        this.noSendList = noSendList;
    }

    public  int getSendTotal(){
        return this.empTotal-this.noSendTotal;
    }


}
