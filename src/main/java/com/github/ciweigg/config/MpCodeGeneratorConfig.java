package com.github.ciweigg.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class MpCodeGeneratorConfig {

    private GlobalConfig globalConfig;
    private DataSourceConfig dataSourceConfig;
    private PackageConfig packageConfig;
    private StrategyConfig strategyConfig;

    public MpCodeGeneratorConfig() {
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public DataSourceConfig getDataSourceConfig() {
        return this.dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public PackageConfig getPackageConfig() {
        return this.packageConfig;
    }

    public void setPackageConfig(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public StrategyConfig getStrategyConfig() {
        return this.strategyConfig;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }
}
