package com.tonydpadua.produto.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static List<Long> decodeIntList(String s){
        String[] vet=s.split(",");
        List<Long> list = new ArrayList<>();
        for(int i=0;i<vet.length;i++){
            list.add(Long.parseLong(vet[i]));
        }
        return list;
        //ou
        //return.Arrays.asList(s.split(",")).stream().map(x->Integer.parseInt(x))
        //.collect(Collectors.toList());
    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
