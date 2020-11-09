# configuration-download-plugin
通过这个Gradle自定义插件可以实现自动从配置中心下载项目的配置文件。

# 1.项目中引入插件

```bash
 buildscript {
    repositories {
		maven {
			url 'http://127.0.0.1:8081/repository/maven-public/'
		}
	}
	
	dependencies {
		classpath group: 'com.dys.gradle.plugin',name: 'configuration-download-plugin',version: '1.0.1-SNAPSHOT'
	}
}


apply plugin: 'configuration.download.plugin'

configPluginExtension {
	name = ["jdbc.properties", "log4j.properties"]
	version = "1.0.0-SNAPSHOT"
}
```
# 2.使用插件
```bash
 gradlew -q configTask
```
