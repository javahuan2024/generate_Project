package com.yh.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class MybatisGenerator {
    private static String authorName = "yh";

    public MybatisGenerator() {
    }

    public void exec(String dbType, String dbUrl, String dbUserName, String dbPassWord, String packageFix, String javaFilePathFix, String[] prefix, String[] tables, List<String> ignoreGenFileTypes) throws IOException, TemplateException {
        String mapperXMLFilePathFix = "/src/main/resources" + javaFilePathFix + "/dao/gen/";
        String classLoaderPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
        String canonicalPath = classLoaderPath.replace("/target/classes/", "");
        DataSourceConfig.Builder dataSourceBuilder = null;
        if (DbType.MYSQL.getDb().equals(dbType)) {
            dataSourceBuilder = (new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassWord)).dbQuery(new MySqlQuery()).typeConvert(new MySqlTypeConvert()).keyWordsHandler(new MySqlKeyWordsHandler());
        }

        if (DbType.ORACLE.getDb().equals(dbType)) {
            dataSourceBuilder = (new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassWord)).dbQuery(new OracleQuery()).typeConvert(new OracleTypeConvert());
        }

        if (DbType.POSTGRE_SQL.getDb().equals(dbType)) {
            dataSourceBuilder = (new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassWord)).dbQuery(new PostgreSqlQuery()).typeConvert(new PostgreSqlTypeConvert()).keyWordsHandler(new PostgreSqlKeyWordsHandler());
        }

        if (dataSourceBuilder == null) {
            throw new RuntimeException("未知的数据库类型，目前只支持MySQL和Oracle！");
        } else {
            FastAutoGenerator autoGenerator = FastAutoGenerator.create(dataSourceBuilder);
            autoGenerator.globalConfig((builder) -> {
                builder.outputDir(canonicalPath + "/src/main/java").disableOpenDir().author(authorName).dateType(DateType.ONLY_DATE).commentDate("yyyy-MM-dd");
            });
            Map<OutputFile, String> pathInfo = new HashMap();
            pathInfo.put(OutputFile.xml, canonicalPath + mapperXMLFilePathFix);
            autoGenerator.packageConfig((builder) -> {
                builder.parent(packageFix).entity("entity.gen").mapper("dao.gen").other("appobject").xml("dao.gen").pathInfo(pathInfo);
            });
            autoGenerator.templateConfig((builder) -> {
                builder.mapper("/genTemplates/mapper.java").xml("/genTemplates/mapper.xml").entity("/genTemplates/entity.java").disable(new TemplateType[]{TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL});
            });
            autoGenerator.strategyConfig((builder) -> {
                builder.addInclude(tables).addTablePrefix(prefix).entityBuilder().enableChainModel().enableLombok().enableTableFieldAnnotation().enableActiveRecord().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).addIgnoreColumns(new String[]{"password"}).addTableFills(new IFill[]{new Column("create_time", FieldFill.INSERT)}).addTableFills(Arrays.asList(new Column("modify_time", FieldFill.INSERT_UPDATE), new Column("update_time", FieldFill.INSERT_UPDATE))).idType(IdType.ASSIGN_UUID).formatFileName("Entity%s").fileOverride().mapperBuilder().enableMapperAnnotation().enableBaseResultMap().enableBaseColumnList().formatMapperFileName("Entity%sGeneratedMapper").fileOverride().formatXmlFileName("Entity%sGeneratedMapper").build();
            });
            FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
            autoGenerator.templateEngine(freemarkerTemplateEngine);
            autoGenerator.execute();
            List<TableInfo> tableInfoList = freemarkerTemplateEngine.getConfigBuilder().getTableInfoList();
            if (null != tableInfoList && tableInfoList.size() > 0) {
                Configuration TEMPLATE_CFG = new Configuration(Configuration.VERSION_2_3_31);
                TEMPLATE_CFG.setDefaultEncoding("UTF-8");
                TEMPLATE_CFG.setClassForTemplateLoading(MybatisGenerator.class, "/genTemplates");
                Template template = TEMPLATE_CFG.getTemplate("appObj.java.ftl");
                template.setOutputEncoding("UTF-8");
                Template paramTemplate = TEMPLATE_CFG.getTemplate("param.java.ftl");
                paramTemplate.setOutputEncoding("UTF-8");
                File appObjectPackageFile = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/appobject");
                if (!appObjectPackageFile.exists()) {
                    appObjectPackageFile.mkdirs();
                }

                File voPackageFile = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/vo");
                if (!voPackageFile.exists()) {
                    voPackageFile.mkdirs();
                }

                File paramPackageFile = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/vo/param");
                if (!paramPackageFile.exists()) {
                    paramPackageFile.mkdirs();
                }

                Template serviceTemplate = TEMPLATE_CFG.getTemplate("service.java.ftl");
                serviceTemplate.setOutputEncoding("UTF-8");
                File servicePackageFile = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/service");
                if (!servicePackageFile.exists()) {
                    servicePackageFile.mkdirs();
                }

                Template controllerTemplate = TEMPLATE_CFG.getTemplate("controller.java.ftl");
                controllerTemplate.setOutputEncoding("UTF-8");
                File controllerPackageFile = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/controller");
                if (!controllerPackageFile.exists()) {
                    controllerPackageFile.mkdirs();
                }

                Template vueTemplate = TEMPLATE_CFG.getTemplate("obj.vue.ftl");
                vueTemplate.setOutputEncoding("UTF-8");
                Template jsTemplate = TEMPLATE_CFG.getTemplate("obj.js.ftl");
                jsTemplate.setOutputEncoding("UTF-8");
                File staticFile = new File(canonicalPath + "/src/main/resources" + javaFilePathFix + "/static");
                if (!staticFile.exists()) {
                    staticFile.mkdirs();
                }

                Date date = new Date();
                Iterator var32 = tableInfoList.iterator();

                while(true) {
                    while(var32.hasNext()) {
                        TableInfo tableInfo = (TableInfo)var32.next();
                        String entityName = tableInfo.getEntityName();
                        String tableName = tableInfo.getName();
                        String tableComment = tableInfo.getComment();
                        Set<String> fieldPackages = new HashSet();
                        String entityPackage = packageFix.concat(".").concat("entity.gen").concat(".").concat(entityName);
                        tableInfo.getFields().forEach((field) -> {
                            IColumnType columnType = field.getColumnType();
                            if (null != columnType && null != columnType.getPkg()) {
                                fieldPackages.add(columnType.getPkg());
                            }

                        });
                        String lowerObjName = entityName.substring(6, entityName.length()).toLowerCase();
                        String upperObjName = entityName.substring(6, entityName.length());
                        String paramPackage = packageFix.concat(".").concat("vo").concat(".").concat("param");
                        String paramObjName = upperObjName.concat("Param");
                        File targetParam = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/vo/param" + File.separator + paramObjName + ".java");
                        if ((ignoreGenFileTypes == null || !ignoreGenFileTypes.contains(GenFileTypeEnum.param.name())) && !targetParam.exists()) {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetParam), "UTF-8"));
                            Map<String, Object> dataModel = new HashMap();
                            dataModel.put("paramPackage", paramPackage);
                            dataModel.put("paramObjName", paramObjName);
                            dataModel.put("table", tableInfo);
                            dataModel.put("fieldPackages", fieldPackages);
                            dataModel.put("author", authorName);
                            dataModel.put("date", String.format(Locale.US, "%1$tb %2$td, %3$tY", date, date, date));
                            paramTemplate.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用Param对象[" + paramPackage + '.' + paramObjName + "]");
                        } else {
                            System.out.println("应用Param对象[" + paramPackage + '.' + paramObjName + "]已经存在，不重复生成");
                        }

                        String appObjectPackage = packageFix.concat(".").concat("appobject");
                        String appObjName = entityName.concat("AO");
                        File targetAppObj = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/appobject" + File.separator + entityName + "AO.java");
                        if ((ignoreGenFileTypes == null || !ignoreGenFileTypes.contains(GenFileTypeEnum.ao.name())) && !targetAppObj.exists()) {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetAppObj), "UTF-8"));
                            Map<String, String> dataModel = new HashMap();
                            dataModel.put("appObjectPackage", appObjectPackage);
                            dataModel.put("entityName", entityName);
                            dataModel.put("entityPackage", entityPackage);
                            dataModel.put("tableName", tableName);
                            dataModel.put("tableComment", tableComment);
                            dataModel.put("date", String.format(Locale.US, "%1$tb %2$td, %3$tY", date, date, date));
                            template.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用对象[" + appObjectPackage + '.' + entityName + "AO]");
                        } else {
                            System.out.println("应用对象[" + appObjectPackage + '.' + entityName + "AO]已经存在，不重复生成");
                        }

                        String generatedMapperPackage = packageFix.concat(".").concat("dao").concat(".").concat("gen").concat(".").concat(entityName).concat("GeneratedMapper");
                        String servicePackage = packageFix.concat(".").concat("service");
                        String baseServicePackage = servicePackage.concat(".share").concat(".BaseService");
                        String serviceObjName = upperObjName.concat("Service");
                        File targetService = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/service" + File.separator + serviceObjName + ".java");
                        if ((ignoreGenFileTypes == null || !ignoreGenFileTypes.contains(GenFileTypeEnum.service.name())) && !targetService.exists()) {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetService), "UTF-8"));
                            Map<String, Object> dataModel = new HashMap();
                            dataModel.put("paramPackage", paramPackage.concat(".").concat(paramObjName));
                            dataModel.put("aoPackage", appObjectPackage.concat(".").concat(appObjName));
                            dataModel.put("mapperPackage", generatedMapperPackage);
                            dataModel.put("servicePackage", servicePackage);
                            dataModel.put("serviceObjName", serviceObjName);
                            dataModel.put("baseServicePackage", baseServicePackage);
                            dataModel.put("table", tableInfo);
                            dataModel.put("fieldPackages", fieldPackages);
                            dataModel.put("objName", upperObjName);
                            dataModel.put("author", authorName);
                            dataModel.put("date", String.format(Locale.US, "%1$tb %2$td, %3$tY", date, date, date));
                            serviceTemplate.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用Service对象[" + servicePackage + '.' + serviceObjName + "]");
                        } else {
                            System.out.println("应用Service对象[" + servicePackage + '.' + serviceObjName + "]已经存在，不重复生成");
                        }

                        String controllerPackage = packageFix.concat(".").concat("controller");
                        String controllerObjName = upperObjName.concat("Controller");
                        File targetController = new File(canonicalPath + "/src/main/java" + javaFilePathFix + "/controller" + File.separator + controllerObjName + ".java");
                        if ((ignoreGenFileTypes == null || !ignoreGenFileTypes.contains(GenFileTypeEnum.controller.name())) && !targetController.exists()) {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetController), "UTF-8"));
                            Map<String, Object> dataModel = new HashMap();
                            dataModel.put("packageFix", packageFix);
                            dataModel.put("paramPackage", paramPackage.concat(".").concat(paramObjName));
                            dataModel.put("aoPackage", appObjectPackage.concat(".").concat(appObjName));
                            dataModel.put("servicePackage", servicePackage.concat(".").concat(serviceObjName));
                            dataModel.put("objName", upperObjName);
                            dataModel.put("controllerPackage", controllerPackage);
                            dataModel.put("controllerObjName", controllerObjName);
                            dataModel.put("path", lowerObjName);
                            dataModel.put("table", tableInfo);
                            dataModel.put("fieldPackages", fieldPackages);
                            dataModel.put("author", authorName);
                            dataModel.put("date", String.format(Locale.US, "%1$tb %2$td, %3$tY", date, date, date));
                            controllerTemplate.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用Controller对象[" + controllerPackage + '.' + controllerObjName + "]");
                        } else {
                            System.out.println("应用Controller对象[" + controllerPackage + '.' + controllerObjName + "]已经存在，不重复生成");
                        }

                        File tarVue = new File(canonicalPath + "/src/main/resources" + javaFilePathFix + "/static" + File.separator + lowerObjName + ".vue");
                        if ((ignoreGenFileTypes == null || !ignoreGenFileTypes.contains(GenFileTypeEnum.vue.name())) && !tarVue.exists()) {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarVue), "UTF-8"));
                            Map<String, Object> dataModel = new HashMap();
                            dataModel.put("objName", lowerObjName);
                            dataModel.put("table", tableInfo);
                            vueTemplate.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用Vue对象[" + tarVue + "]");
                        } else {
                            System.out.println("应用Vue对象[" + tarVue + "]已经存在，不重复生成");
                        }

                        File tarJs = new File(canonicalPath + "/src/main/resources" + javaFilePathFix + "/static" + File.separator + lowerObjName + ".js");
                        if (ignoreGenFileTypes != null && ignoreGenFileTypes.contains(GenFileTypeEnum.vue.name()) || tarJs.exists()) {
                            System.out.println("应用Js对象[" + tarJs + "]已经存在，不重复生成");
                        } else {
                            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarJs), "UTF-8"));
                            Map<String, Object> dataModel = new HashMap();
                            dataModel.put("objName", lowerObjName);
                            dataModel.put("table", tableInfo);
                            jsTemplate.process(dataModel, writer);
                            writer.flush();
                            writer.close();
                            System.out.println("生成应用Js对象[" + tarJs + "]");
                        }
                    }

                    return;
                }
            }
        }
    }
}
