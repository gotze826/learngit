package com.bjtu.redis;

import java.io.*;

public class ReadJson {


    /**
     * 读取JSON文件
     */
    public static String ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String str = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            for (int line = 1; (tempString = reader.readLine()) != null; line++) {
                str += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    /**
     * 写入JSON文件
     */
    public static void WriteFile(String filePath, String sets) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }


}
