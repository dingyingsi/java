package com.dys.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ConfigTask extends DefaultTask {
	
	static final String CONFIG_CENTER_URL = "http://192.168.127.137:8080/config-center-0.0.1-SNAPSHOT/";

	@TaskAction
	void download() {
	
		def names = project.configPluginExtension.name;
		def version = project.configPluginExtension.version;
		
		names.each { name -> 
			
			def inputStream = new URL(CONFIG_CENTER_URL + name).openStream();
			
			println ("${project.projectDir} , ${project.buildDir}, ${project.rootDir}, ${project.resources}");
			
			def file = new File("${project.projectDir}/src/main/resources/${name}");
			file.withOutputStream { 
				outputStream -> 
					outputStream << inputStream;
			}
		}
	}
	
}