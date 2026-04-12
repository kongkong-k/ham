package cn.tx.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    //反射
    public void generator() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        // 关键：替换为你generatorConfig.xml的绝对路径
        File configFile = new File("D:\\IdeaProjects2017\\hamiprent\\hami_core\\src\\main\\resources\\generatorConfig.xml");

        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration config = parser.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("逆向工程代码生成成功！");
    }

    public static void main(String[] args) {
        try {
            Generator generator = new Generator();
            generator.generator();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("逆向工程代码生成失败：" + e.getMessage());
        }
    }
}