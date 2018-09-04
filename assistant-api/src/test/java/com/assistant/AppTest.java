//package com.assistant;
//
//
//import org.junit.Test;
//import strman.Strman;
//
//import java.io.*;
//import java.util.Arrays;
//
///**
// * Unit test for simple App.
// */
//public class AppTest {
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void shouldAnswerWithTrue() {
//
//
//        String content = readToString("C:\\Users\\JASHION1\\Desktop\\新建文本文档.txt");
//        String[] inter = Strman.between(content, "/", "\\{@");
//        fileChaseFW(inter);
////        for (String in : inter) {
////            System.out.println(in);
////        }
//    }
//
//    /**
//     * 写入TXT，追加写入
//     */
//    public static void fileChaseFW(String[] content) {
//        try {
//            //构造函数中的第二个参数true表示以追加形式写文件
//            FileWriter fw = new FileWriter("C:\\Users\\JASHION1\\Desktop\\新建文本文档 (2).txt", true);
//            for (String in : content) {
//                fw.write(in+"\r\n");
//            }
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("文件写入失败！" + e);
//        }
//    }
//
//
//    /**
//     * 一次性读取文件内容
//     *
//     * @param filePath 文件路径
//     * @return 文件内容
//     */
//    private String readToString(String filePath) {
//
//        String encoding = "UTF-8";
//        File file = new File(filePath);
//        String fileContent = "";
//
//        try {
//
//            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
//            String line;
//            while ((line = bf.readLine()) != null) {
//                fileContent = fileContent + "\r\n" + line;
//            }
//            bf.close();
//            return fileContent;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
//}
