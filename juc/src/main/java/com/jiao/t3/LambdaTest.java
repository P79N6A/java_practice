package com.jiao.t3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jiao on 12/29/2018.
 */
public class LambdaTest extends JFrame {
    private JButton jb;

    public LambdaTest() throws HeadlessException {
        this.setBounds(200,200,400,200);
        this.setTitle("测试");
        this.jb = new JButton("点击一下");
        jb.addActionListener( e -> System.out.println("hello") );
        this.add(jb);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new LambdaTest();
    }

}
