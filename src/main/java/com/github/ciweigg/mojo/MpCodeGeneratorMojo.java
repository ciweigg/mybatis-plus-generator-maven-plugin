package com.github.ciweigg.mojo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.github.ciweigg.config.MpCodeGeneratorConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 插件生成代码测试
 */
@Mojo(name = "generator", defaultPhase = LifecyclePhase.COMPILE)
public class MpCodeGeneratorMojo extends AbstractMojo {

    private static final String DEFAULT_PATH = "mp-code-generator-config.yaml";

    @Parameter
    private String configurationFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        InputStream inputStream = null;
        if (!StringUtils.isEmpty(configurationFile)) {
            try {
                inputStream = new FileInputStream(configurationFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = MpCodeGeneratorMojo.class.getClassLoader().getResourceAsStream(DEFAULT_PATH);
        }

        MpCodeGeneratorConfig config = yaml2Config(inputStream);
        AutoGenerator mpg = configureAutoGenerator(config);
        mpg.execute();
    }


    private MpCodeGeneratorConfig yaml2Config(InputStream inputStream) {
        Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        return yaml.loadAs(inputStream, MpCodeGeneratorConfig.class);
    }

    private AutoGenerator configureAutoGenerator(MpCodeGeneratorConfig config) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(config.getGlobalConfig());
        mpg.setDataSource(config.getDataSourceConfig());
        mpg.setPackageInfo(config.getPackageConfig());
        mpg.setTemplate(config.getTemplateConfig());
        mpg.setStrategy(config.getStrategyConfig());
        if(config.isUseftl()){
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        }else if(config.isUsebtl()){
            mpg.setTemplateEngine(new BeetlTemplateEngine());
        }else {
            //默认使用vm
            mpg.setTemplateEngine(new VelocityTemplateEngine());
        }
        return mpg;
    }
}
