package com.sansheng.dd;


public class test {
    public static void main(String[] args) {
        String[] nms=new String[]{"client_id","client_secret","grant_type","phone","timestamp"};
        String[] values=new String[]{"253f35c96cfc23fe6c9c84081f4f3d8a","e92706ce8491f96275430895e0132f8d","client_credentials","18221698899","1617992872425"};
        String signKey = "f624F03Ceb23C7465431";
        String sign = ddsign.genSign(nms,values, signKey);
        System.out.println(sign);
    }

}
