/*
* 模板生成器的类
* 利用java基础，I/O流读取文件并生成新文件
* */

package cn.tx.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SourceGenerator {

    // 主函数
    public static void main(String[] args) throws Exception {
        //调用了模板生成的方法  静态/类方法
        generatorSource("Song");
    }

    public static void generatorSource(String objName) throws Exception{
        //generateQuery(objName);
        //生成dao层接口
        generateDao(objName);
        //生成业务层
        generateService(objName);
        //service层实现类
        generateServiceImpl(objName);
    }


    public static void generateQuery(String objName) throws Exception{
        //EbItem
        //创建文件输入流
        BufferedReader br = new BufferedReader(new FileReader("hami_core/src/test/resources/tpl/DemoQuery.tpl"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hami_core/src/main/java/cn/tx/query/"+objName+"Query.java"));
        String line = null;
        while((line = br.readLine()) != null){
            line = line.replace("Demo", objName);
            bw.write(line);
            bw.newLine();

        }
        bw.close();
        br.close();
    }
    public static void generateDao(String objName) throws Exception{
        //EbItem
        //创建文件输入流
        BufferedReader br = new BufferedReader(new FileReader("hami_core/src/test/resources/tpl/DemoMapper.tpl"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hami_core/src/main/java/cn/tx/dao/"+objName+"Mapper.java"));
        String line = null;
        while((line = br.readLine()) != null){
            line = line.replace("Demo", objName);
            bw.write(line);
            bw.newLine();

        }
        bw.close();
        br.close();
    }
    public static void generateService(String objName) throws Exception{
        //EbItem
        //创建文件输入流
        BufferedReader br = new BufferedReader(new FileReader("hami_core/src/test/resources/tpl/DemoService.tpl"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hami_core/src/main/java/cn/tx/service/"+objName+"Service.java"));
        String line = null;
        while((line = br.readLine()) != null){
            line = line.replace("Demo", objName);
            bw.write(line);
            bw.newLine();

        }
        bw.close();
        br.close();
    }
    public static void generateServiceImpl(String objName) throws Exception{

        //把传递过来的实体类的名字改成小写一份
        //songer
        String lowerObjName = objName.substring(0, 1).toLowerCase()+objName.substring(1, objName.length());

        //EbItem
        //创建文件输入流
        BufferedReader br = new BufferedReader(new FileReader("hami_core/src/test/resources/tpl/DemoServiceImpl.tpl"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hami_core/src/main/java/cn/tx/service/impl/"+objName+"ServiceImpl.java"));
        String line = null;
        while((line = br.readLine()) != null){
            line = line.replace("Demo", objName);
            line = line.replace("demo", lowerObjName);
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
    }

}