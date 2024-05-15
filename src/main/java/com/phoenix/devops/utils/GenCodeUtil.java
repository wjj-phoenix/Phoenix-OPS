package com.phoenix.devops.utils;

import java.time.LocalDate;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author wjj-phoenix
 * @since 2024-05-14
 */
public class GenCodeUtil {
    public static void main(String[] args) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://192.168.156.24:3306/phoenix_ops?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("W&l1203@");

        // 创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置根包
        globalConfig.setBasePackage("com.phoenix.devops");
        globalConfig.setAuthor("wjj-phoenix");
        globalConfig.setSince(LocalDate.now().toString());

        // 设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setTableDefGenerateEnable(true);
        globalConfig.enableEntity().setWithLombok(true);
        globalConfig.setEntityWithLombok(true);
        // 设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(21);

        // 设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getMapperConfig().setMapperAnnotation(true);
        globalConfig.setMapperXmlGenerateEnable(true);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceClassPrefix("I");

        globalConfig.setServiceImplGenerateEnable(true);

        globalConfig.setControllerGenerateEnable(true);

        return globalConfig;
    }
}
