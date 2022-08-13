package com.example.generator;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    //采用控制台交互
    public static String scanner(String trip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append("请输入：" + trip + ":");
        System.out.println(sb.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + trip + "！");
    }


    public static void main(String[] args) {
        //代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/yeb-generator/src/main/java");
        gc.setAuthor("mango");
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setActiveRecord(true);
//        gc.setEnableCache(true);
        gc.setFileOverride(true); //文件覆盖
         gc.setSwagger2(true); //实体属性 Swagger2 注解
        autoGenerator.setGlobalConfig(gc);

        //数据源配置 postgre
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/yeb?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        autoGenerator.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.server");
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        autoGenerator.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do something
            }
        };
        //模板引擎类型
        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 如果模板引擎是 beetl
        String templatePath = "/templates/mapper.xml.btl";

        //自定输出配置
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        fileOutConfigs.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/yeb-generator/src/main/resources/mapper/" +
                        tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(fileOutConfigs);
        autoGenerator.setCfg(cfg);

        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置下划线命名规则
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setSuperEntityClass("");  //实体类设置父类
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);

        // 公共父类
//        strategyConfig.setSuperControllerClass("");  //控制层设置父类
        // 写于父类中的公共字段
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix("t_");

        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new BeetlTemplateEngine());
        autoGenerator.execute();
    }
}

