package com.dys.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class ConfigPlugin implements Plugin<Project> {

	void apply(Project project) {
		project.extensions.create("configPluginExtension", ConfigPluginExtension)
		Task task = project.task("configTask", type: ConfigTask)
	}
}
