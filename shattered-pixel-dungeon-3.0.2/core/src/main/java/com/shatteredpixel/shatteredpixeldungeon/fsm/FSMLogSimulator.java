package com.shatteredpixel.shatteredpixeldungeon.fsm;

import java.util.Random;

public class FSMLogSimulator {

    public static void main(String[] args) {
        String currentState = "S0";
        Random rand = new Random();
        int steps = 50; // 模拟多少次状态转移

        for (int i = 0; i < steps; i++) {
            switch (currentState) {
                case "S0":
                    FSMLogger.log("S0", "E1", "S1");
                    currentState = "S1";
                    break;

                case "S1":
                    FSMLogger.log("S1", "E2", "S2");
                    currentState = "S2";
                    break;

                case "S2":
                    FSMLogger.log("S2", "E3", "S3");
                    currentState = "S3";
                    break;

                case "S3":
                    // 80% 成功截图，20% 模拟失败
                    if (rand.nextDouble() < 0.8) {
                        FSMLogger.log("S3", "E4", "S4");
                        currentState = "S4";
                    } else {
                        FSMLogger.log("S3", "E5", "S5");
                        currentState = "S5";
                    }
                    break;

                case "S4":
                    FSMLogger.log("S4", "E1", "S1"); // 循环回到再次截图
                    currentState = "S1";
                    break;

                case "S5":
                    FSMLogger.log("S5", "E0", "S0"); // 错误处理完成回到空闲
                    currentState = "S0";
                    break;

                default:
                    currentState = "S0";
            }

            // 可选：模拟时间间隔（让每条日志更自然）
            try {
                Thread.sleep(rand.nextInt(150) + 50); // 50~200ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("FSM simulation log generated.");
    }
}
