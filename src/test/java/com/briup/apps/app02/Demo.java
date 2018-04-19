package com.briup.apps.app02;/**
 * Created by ASUS on 2018/3/31.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Demo  {

    public static void demo(String a){
        a.substring(3);
        a.concat("z");

    }
    public static void main(String[] args) {
    String a  = "ABCDE";
    demo(a);
    System.out.print(a);

    }


}
