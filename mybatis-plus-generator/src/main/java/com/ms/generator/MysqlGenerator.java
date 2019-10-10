package com.ms.generator;

import java.io.File;
import java.util.*;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    private static final String MODULE_NAME = "module-member";
    private static final String PARENT_PACKAGE = "com.ms";
    private static final String AUTHOR = "Zhu Kaixiao";
    private static final Class ID_TYPE = Long.class;


    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/msdemo?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/" + MODULE_NAME + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setSwagger2(true);
        //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(JDBC_DRIVER_NAME);
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                HashMap<String, Object> map = Maps.newHashMap();

                if (!ID_TYPE.getName().startsWith("java.lang")) {
                    map.put("idImportName", ID_TYPE.getName());
                }

                map.put("idType", ID_TYPE.getSimpleName());
                map.put("entityUid", new Random().nextLong());
                this.setMap(map);
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String packagePath = PARENT_PACKAGE.replaceAll("\\.", "/");
                return projectPath + "/" + MODULE_NAME +
                "/src/main/java/" + packagePath + "/" + pc.getModuleName() + "/mapper/xml/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.ms.core.kit.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setSuperControllerClass("com.ms.core.kit.BaseController");
        strategy.setSuperServiceClass("com.ms.core.kit.BaseService");
        strategy.setSuperServiceImplClass("com.ms.core.kit.BaseServiceImpl");
        strategy.setInclude(scanner("表名"));
        strategy.setSuperEntityColumns("id", "create_user", "create_time", "update_user", "update_time");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix("jc_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

}
