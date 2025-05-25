package com.shatteredpixel.shatteredpixeldungeon.fsm;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FSMLogger {

    // 写入的日志文件路径（生成在项目根目录）
    private static final String LOG_FILE_PATH = "fsm_log.csv";

    // 固定用户名（如需动态改写可扩展）
    private static final String USER = "testUser";

    /**
     * 写一条状态转移日志到 CSV 文件中
     * @param currentState 当前状态（如 S0）
     * @param event 触发事件（如 E1）
     * @param nextState 下一状态（如 S1）
     */
    public static void log(String currentState, String event, String nextState) {
        try (
            FileWriter fw = new FileWriter(LOG_FILE_PATH, true); // true = 追加模式
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
        ) {
            // 获取当前时间
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

            // 输出格式：时间戳,用户名,当前状态,事件,下一状态
            out.printf("%s,%s,%s,%s,%s%n", timestamp, USER, currentState, event, nextState);

        } catch (IOException e) {
            System.err.println("Failed to write FSM log: " + e.getMessage());
        }
    }
}
