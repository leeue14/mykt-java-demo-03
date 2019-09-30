package com.leeue.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器<br>
 * <p>
 * 需要生成文件和包名 保证格式的规范化<br>
 *
 * @author liyue
 * @date 2019/9/26 18:29
 */
public class CodeGen {
    //1.规定需要生成的文件路径
    public static String OUT_PUT_DIR = "/Users/mac/Documents/09myworkplace/03mykt_demo/001_常用的工具类";
    //2.设置创建的人
    public static String GEN_AUTHOR = "leeue";
    //3.实体类对应的数据库表名称
    private static String[] TABLES = {"user"};
    //大的模块名称
    private static String LARGE_MODULE = "user";
    // private static String MODULE = "ttd";
    // private static String SUBMODULE = "articles";
    private static String database = "test";
    private static String PACKAGE_NAME = "com.leeue.user";

    public static void main(String[] args) {
        //定义生成器
        AutoGenerator mpg = new AutoGenerator();

        String rootDir = OUT_PUT_DIR + "/src/main/java/";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rootDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setSwagger2(true);
        gc.setAuthor(GEN_AUTHOR);
        gc.setDateType(DateType.TIME_PACK);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://120.78.185.72:3306/" + database + "?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[]{""});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(TABLES); // 需要生成的表
        strategy.setEntityLombokModel(true);
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.carelinker.common.mybatis.service.IClService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.carelinker.common.mybatis.service.ClServiceImpl");

        //strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);


        // 包配置
        PackageConfig pc = new PackageConfig();


        pc.setParent("");
        pc.setController(PACKAGE_NAME + ".controller");
        pc.setEntity(PACKAGE_NAME + ".entity");
        pc.setMapper(PACKAGE_NAME + ".mapper");
        pc.setService(PACKAGE_NAME + ".service");
        pc.setServiceImpl(PACKAGE_NAME + ".service.impl");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        String finalApiPackage1 = PACKAGE_NAME;
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                map.put("packageDto", finalApiPackage1 + ".dto");
                map.put("packageStructMapper", finalApiPackage1 + ".mapstruct");
                this.setMap(map);
            }
        };
        String finalApiPackage = PACKAGE_NAME;
        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/dto.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return rootDir+PACKAGE_NAME.replaceAll("\\.", "/") + "/dto/" + tableInfo.getEntityName() + "DTO.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        // 调整 mapper 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return OUT_PUT_DIR + "/src/main" + "/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }

        });

        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return rootDir+PACKAGE_NAME.replaceAll("\\.", "/") + "/entity/" + tableInfo.getEntityName() + "DTO.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig tc = new TemplateConfig();
//         tc.setController("...");
        tc.setEntity(null);
//         tc.setMapper("...");
        tc.setXml(null);
//         tc.setService("...");
//         tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }

}
