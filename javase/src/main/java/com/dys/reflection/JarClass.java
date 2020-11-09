package com.dys.reflection;

import java.io.File;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JarClass {
    private static final Logger logger = LogManager.getLogger(JarClass.class);

    private static Set<String> getTypeAliasesPackageFromFileAndJar(String typeAliasesPackage) {
        Set<String> packageNames = new HashSet<String>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            String typeAliasesPackagePath = typeAliasesPackage.replace(".", "/");
            Enumeration<URL> urls = classLoader.getResources(typeAliasesPackagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                logger.debug(url.getPath());
                if (url != null) {
                    String protocol = url.getProtocol();
                    switch (protocol) {
                        case "file":
                            packageNames.addAll(extractPackageFromFile(url, typeAliasesPackage));
                            break;
                        case "jar":
                            packageNames.addAll(extractPackageFromJar(url, typeAliasesPackage));
                            break;
                        default:
                            ;
                    }
                }
            }
        } catch (Exception e) {
        }
        return packageNames;
    }
    private static Set<String> extractPackageFromFile(URL url, String typeAliasesPackage) throws Exception {
        List<String> packageNames = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        String packagePath = typeAliasesPackage.replace(".", File.separator);
        String path = url.getPath();
        File file = new File(path);
        findClass(file, packageNames);
        for (int i = 0; i < packageNames.size(); i++) {
            String pkgName = packageNames.get(i);
            String classNamePath = pkgName.substring(pkgName.indexOf(packagePath), packageNames.get(i).length())
                    .replace(".class", "");
            String className = classNamePath.replace(File.separator, ".");
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Alias.class)) {
                set.add(classNamePath.substring(0, classNamePath.lastIndexOf(File.separator)).replace(File.separator,
                        "."));
            }
        }
        return set;
    }

    private static void findClass(File file, List<String> packageNames) {
        if (file.exists() && file.isDirectory()) {
            for (File f : file.listFiles()) {
                findClass(f, packageNames);
            }
        }
        if (file.getName().endsWith(".class")) {
            packageNames.add(file.getAbsolutePath());
        }
    }
    private static Set<String> extractPackageFromJar(URL url, String typeAliasesPackage) throws Exception {
        Set<String> packageNames = new HashSet<String>();
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String jarEntryName = jarEntry.getName();
            if (jarEntryName.endsWith(".class")) {
                String className = jarEntryName.substring(0, jarEntryName.indexOf(".class")).replace("/", ".");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Alias.class)) {
                    String packageName = className.substring(0, className.lastIndexOf("."));
                    packageNames.add(packageName);
                }
            }
        }
        return packageNames;
    }
}
