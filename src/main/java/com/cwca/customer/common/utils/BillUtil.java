package com.cwca.customer.common.utils;

import java.util.Arrays;
import java.util.List;

public class BillUtil {
    private static String[] hxbill = {"zydm", "zyxm", "bmmc", "jbgz", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs"
            , "yjjx", "tc", "jbf", "kqkk", "flf", "jtf", "cnf", "cnfksbf", "txf", "zxjl", "qt", "qtkk", "flhj", "yfgz", "ylbx", "sybx", "yilbx"
            , "zfgjj", "qynj", "dkgs", "sfgz"};
    private static String[] wbbill = {"xf", "zyxm", "bmmc", "tzhyxbz", "yyxbz", "txfybz", "txfxbz", "txfce", "lzjtf", "jbgz",
            "gzjxgzjs", "gzjxkhxs", "qt", "tc", "yjjxgzjs", "yjjxkhxs", "jbbz", "zxjl", "txf", "kqkk", "qtkk", "yfgz", "ylbx", "yilbx",
            "sybx", "gjj", "kqynj", "flf", "ynssde", "kgrsds", "sfgz", "jrtxfyhzzsf"};
    private static String[] cabill = {"zydm", "zyxm", "bmmc", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs", "yjjx", "tc"
            , "kqkk", "cnf", "cnfksbf", "txf", "flf", "gjf", "jj", "flhj", "jbbz", "zxjl", "qt", "kshj", "yfgz", "ylbx", "sybx", "yilbx", "zfgjj"
            , "qynj", "dkgs", "qtkk", "sfgz"};



    public static List<String> getHxbill(){
        final List <String> hxbilllist = Arrays.asList(hxbill);
        return hxbilllist;
    }

    public static List<String> getWbbill(){
        final List <String> wbbilllist = Arrays.asList(wbbill);
        return wbbilllist;
    }

    public static List<String> getCabill(){
        final List <String> cabilllist = Arrays.asList(cabill);
        return cabilllist;
    }

}