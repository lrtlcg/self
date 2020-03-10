package com.code;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyCodeGenteration {
	public static void main(String[] args) {
		MyCodeGenteration.Zcode(new String[] { "bus_" },
				new String[] { "bus_goodMsg"});
		//new String[] { "sys_stat","sys_user", "sys_role", "sys_user_role", "sys_account", "sys_pros", "sys_role_pros" });
	}

	/**
	 * 自动生成代码
	 * 
	 * @param tabelPrefix 表名前缀
	 * @param tableName   表名，根据表名称，自动生成
	 */
	public static void Zcode(String[] tabelPrefix, String[] tableName) {
		AutoGenerator mpg = new AutoGenerator();// 代碼生成器
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");// 項目目錄
		gc.setOutputDir(projectPath + "/src/main/java");// 代碼保存目標目錄
		gc.setFileOverride(true);// 可以覆蓋已有文件
		gc.setActiveRecord(true);// 使用AR模式，一行記錄對應一個實例
		gc.setEnableCache(false);// xml緩存
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(false);
		gc.setAuthor("liucg");
		// 全局命名規則
		gc.setControllerName("%scontroller");
		gc.setServiceName("%sservice");
		gc.setServiceImplName("%sserviceImpl");
		gc.setMapperName("%sMapper");
//		gc.setEntityName("%sEntity");
		gc.setXmlName("%sMapper");

		mpg.setGlobalConfig(gc);//

		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/self?useSSl=false&serverTimezone=GMT%2B8");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("saadmin");
		mpg.setDataSource(dsc);// 代码生成数据源

		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix(tabelPrefix);// 表前綴,生成類時，按照規則過濾表名字段前綴
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略（下劃線變駝峯）
		strategy.setInclude(tableName);// 根據表名 逆向

//		strategy.setSuperEntityColumns(new String[] {
//				"uperdate_time"
//		});//設置類中公共字段

		strategy.setSuperServiceClass(null);// 生成service的接口，比如：“org.ibase4j.core.base.BaseMapper”
		strategy.setSuperMapperClass(null);
		strategy.setSuperMapperClass(null);

		mpg.setStrategy(strategy);//

		// 配置代碼生成的包
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.liucg");// 包名
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setMapper("dao");
		pc.setEntity("pojo");
		pc.setXml("xml");
		mpg.setPackageInfo(pc);
		// 執行生成
		mpg.execute();

	}
}
