package com.bjtu.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static com.bjtu.redis.ActionExe.Execute;

/*
主函数
 */
public class Main {
    private static List<CounterSpec> counters;
    private static JSONObject actions;
    private static RedisUtil redisUtil;
    private static FileMonitor m;

    static {
        try {
            m = new FileMonitor(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        redisUtil = new RedisUtil(JedisInstance.getInstance().getResource());
        m.monitor("src/json/", new FileListener());
        m.start();
        LoadJson();
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                mainFrame();
                String input = br.readLine();
                if(input.equals("0")) {
                    System.out.println("成功退出");
                    break;
                }else{
                    input_option(input);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 加载JSON文件并更新counter/action对象
     */
    public static void LoadJson() {
        String counterStr = ReadJson.ReadFile("src/json/counter.json");
        String actionStr = ReadJson.ReadFile("src/json/action.json");
        counters = JSON.parseArray(JSON.parseObject(counterStr, JSONObject.class)
                .getJSONArray("counters").toJSONString(), CounterSpec.class);
        actions = JSON.parseObject(actionStr, JSONObject.class).getJSONObject("actions");

    }

    /**
     * 主界面提示
     */
    public static void mainFrame(){
        System.out.println("0.退出");
        System.out.println("1.USER_INCR");
        System.out.println("2.USER_DECR");
        System.out.println("3.USER_FREQ_INCR");
        System.out.println("4.USER_FREQ_DECR");
        System.out.println("请选择：");
    }

    /**
     * 输入后的操作
     * @param input
     */
    public static void input_option(String input){
        switch (input) {
            case "1":
                input_action("USER_INCR");
                break;
            case "2":
                input_action("USER_DECR");
                break;
            case "3":
                input_action("USER_FREQ_INCR");
                break;
            case "4":
                input_action("USER_FREQ_DECR");
                break;
            default:
                System.out.println("输入错误");
                break;
        }
}
    /**
     * 调用action操作
     * @param act
     */
    public static void input_action(String act){
        ActionSpec action = new ActionSpec();
        action.setType(act);
        action.setFeature_retrieve(JSON.parseArray(actions.getJSONObject(act)
                .getJSONArray("feature_retrieve").toJSONString(), String.class));
        action.setSave_counter(JSON.parseArray(actions.getJSONObject(act)
                .getJSONArray("save_counter").toJSONString(), String.class));
        Execute(action,counters,redisUtil);
    }


}
