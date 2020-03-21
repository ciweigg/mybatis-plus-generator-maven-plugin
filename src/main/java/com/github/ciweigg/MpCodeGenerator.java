package com.github.ciweigg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.github.ciweigg.config.MpCodeGeneratorConfig;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.FileInputStream;
import java.io.InputStream;

public class MpCodeGenerator {

	private static final String DEFAULT_PATH = "mp-code-generator-config.yaml";

	public MpCodeGenerator() {
	}

	public static void main(String[] args) throws Exception {
		Object inputStream;
		if (args.length > 0 && !StringUtils.isEmpty(args[0])) {
			inputStream = new FileInputStream(args[0]);
		} else {
			inputStream = MpCodeGenerator.class.getClassLoader().getResourceAsStream(DEFAULT_PATH);
		}

		MpCodeGeneratorConfig config = yaml2Config((InputStream)inputStream);
		AutoGenerator mpg = configureAutoGenerator(config);
		mpg.execute();
	}

	private static MpCodeGeneratorConfig yaml2Config(InputStream inputStream) {
		Yaml yaml = new Yaml();
		yaml.setBeanAccess(BeanAccess.FIELD);
		return yaml.loadAs(inputStream, MpCodeGeneratorConfig.class);
	}

	private static AutoGenerator configureAutoGenerator(MpCodeGeneratorConfig config) {
		AutoGenerator mpg = new AutoGenerator();
		mpg.setGlobalConfig(config.getGlobalConfig());
		mpg.setDataSource(config.getDataSourceConfig());
		mpg.setPackageInfo(config.getPackageConfig());
		mpg.setTemplate(new TemplateConfig());
		mpg.setStrategy(config.getStrategyConfig());
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		return mpg;
	}

}
