package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Mgr;
import com.jiao.proxy.pojo.Pay;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
public class EmpVO extends Emp {
    List<Attend> attends;
    List<Pay> pays;
    Mgr mgr;
}
