package com.bjtu.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class ActionSpec {
    public static Jedis jedis= JedisInstance.getInstance().getResource();
    /**
     * 操作类型-枚举
     */
    private enum ActionType {
        USER_INCR,
        USER_DECR,
        USER_FREQ_INCR,
        USER_FREQ_DECR,
    }

    private ActionType type;
    private List<String> feature_retrieve;
    private List<String> save_counter;


    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        switch (type) {
            case "USER_INCR":
                this.type = ActionType.USER_INCR;
                break;
            case "USER_DECR":
                this.type = ActionType.USER_DECR;
                break;
            case "USER_FREQ_INCR":
                this.type = ActionType.USER_FREQ_INCR;
                break;
            case "USER_FREQ_DECR":
                this.type = ActionType.USER_FREQ_DECR;
                break;
            default:
                System.out.println("指令类别无效！");
                break;
        }
    }

    public List<String> getFeature_retrieve() {
        return feature_retrieve;
    }

    public void setFeature_retrieve(List<String> feature_retrieve) {
        this.feature_retrieve = feature_retrieve;
    }

    public List<String> getSave_counter() {
        return save_counter;
    }

    public void setSave_counter(List<String> save_counter) {
        this.save_counter = save_counter;
    }

    public static void showList(){
        List<String> list = jedis.lrange("UserList", 0, -1 );
        System.out.println(list);
    }

    public static void showSet(){
        Set<String> set = jedis.smembers("UserSet");
        System.out.println(set);
    }

    public void showZSet(){
        Set<String> zset = jedis.zrange("UserZSet", 0, -1);
        System.out.println(zset);
    }

}
