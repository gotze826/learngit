package com.bjtu.redis;

import java.util.ArrayList;
import java.util.List;

public class ActionExe {
    public static void Execute(ActionSpec act,List<CounterSpec> counters,RedisUtil redisUtil) {
        List<CounterSpec> retrieve = new ArrayList<CounterSpec>();
        List<CounterSpec> save = new ArrayList<CounterSpec>();
        for (CounterSpec ctr : counters) {
            if (act.getFeature_retrieve().contains(ctr.getCounterName())) {
                retrieve.add(ctr);
            }
            if (act.getSave_counter().contains(ctr.getCounterName())) {
                save.add(ctr);
            }
        }
        switch (act.getType()) {
            case "USER_INCR":
                for (CounterSpec ctr : retrieve) {
                    System.out.println("key：" + ctr.getKey_Fields());
                    System.out.println("操作前value：" + redisUtil.get(ctr.getKey_Fields()));
                }
                for (CounterSpec ctr : save) {
                    redisUtil.incr(ctr.getKey_Fields(), Long.parseLong(ctr.getValue_Fields()));
                    System.out.println("操作后value：" + redisUtil.get(ctr.getKey_Fields()));
                }
                break;
            case "USER_DECR":
                for (CounterSpec ctr : retrieve) {
                    System.out.println("key：" + ctr.getKey_Fields());
                    System.out.println("操作前value：" + redisUtil.get(ctr.getKey_Fields()));
                }
                for (CounterSpec ctr : save) {
                    redisUtil.decr(ctr.getKey_Fields(), Long.parseLong(ctr.getValue_Fields()));
                    System.out.println("操作后value：" + redisUtil.get(ctr.getKey_Fields()));
                }
                break;
            case "USER_FREQ_INCR":
                for (CounterSpec ctr : retrieve) {
                    System.out.println("key：" + ctr.getKey_Fields());
                    System.out.println("field：" + ctr.getField());
                    System.out.println("操作前value：" + redisUtil.hget(ctr.getKey_Fields(), ctr.getField()));
                }
                for (CounterSpec ctr : save) {
                    redisUtil.hincr(ctr.getKey_Fields(), ctr.getField(), Long.parseLong(ctr.getValue_Fields()));
                    System.out.println("操作后value：" + redisUtil.hget(ctr.getKey_Fields(), ctr.getField()));
                }
                break;
            case "USER_FREQ_DECR":
                for (CounterSpec ctr : retrieve) {
                    System.out.println("key：" + ctr.getKey_Fields());
                    System.out.println("field：" + ctr.getField());
                    System.out.println("操作前value：" + redisUtil.hget(ctr.getKey_Fields(), ctr.getField()));
                }
                for (CounterSpec ctr : save) {
                    redisUtil.hdecr(ctr.getKey_Fields(), ctr.getField(), Long.parseLong(ctr.getValue_Fields()));
                    System.out.println("操作后value：" + redisUtil.hget(ctr.getKey_Fields(), ctr.getField()));
                }
                break;
            default:
                System.out.println("指令类别无效！");
                break;
        }
        System.out.println();
    }


}
