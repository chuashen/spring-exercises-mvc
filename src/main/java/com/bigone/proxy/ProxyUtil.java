package com.bigone.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态代理工具类
 * 动态生成代理类文件，编译，加载，调用执行
 *
 * 使用io拼装写java文件，反射class
 *
 * */
public class ProxyUtil {

    /**
     * 传入目标对象，动态生成代理对象
     * 本地模拟
     * */
    public static Object newInstance(Object target){
        Object proxy = null;
        //获取第一个接口类--UserService
        Class targetInf = target.getClass().getInterfaces()[0];
        //接口中的所有方法
        Method methods[] = targetInf.getDeclaredMethods();
        targetInf.getMethods();
        //换行符
        String line = "\n";
        //tab缩进
        String tab = "\t";
        //目标接口名称
        String infName = targetInf.getSimpleName();
        StringBuffer sb = new StringBuffer();
        //添加包名
        sb.append("package com.bigone;");
        sb.append(line);
        //导入import信息
        //导入接口类
        sb.append("import ");
        sb.append(targetInf.getName());//接口全名
        sb.append(";"+line);
        //类声明第一行
        sb.append("public class $Proxy implements ");
        sb.append(infName + "{"+line);
        //属性
        sb.append(tab+"private "+infName+" target;"+line);
        //构造方法
        sb.append(tab);
        sb.append("public $Proxy (" + infName +" target){" + line);
        sb.append(tab + tab + "this.target = target;");
        sb.append(line+tab+"}"+line);
        //方法体
        for(Method method : methods){
            //方法返回类型
            String returnTypeName = method.getReturnType().getName();
            //方法名称
            String methodName = method.getName();
            //方法中的参数
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for(Class arg : args){
                //int age,UserInfo user
                argsContent += arg.getName() + " param" + flag + ",";
                paramsContent += "param" + flag + ",";
                flag ++ ;
            }
                if(argsContent.length()>0){
                argsContent=argsContent.substring(0,argsContent.lastIndexOf(",")-1);
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(",")-1);
            }

            sb.append(tab + "public " + returnTypeName + " " + methodName);
            sb.append("(" + argsContent + "){" + line);
            //添加织入代理逻辑
            sb.append(tab+tab+"System.out.println(\"执行代理逻辑--log---\");"+line);
            if(!"void".equals(returnTypeName)){
                sb.append(tab+tab+"return target." + methodName + "(" + paramsContent+");" + line);
            }
            sb.append(tab+"}"+line);
        }
        sb.append("}");

        //将java文件生成到本地
        File file =new File("E:\\code\\chuashen\\com\\bigone\\$Proxy.java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(sb.toString());
            fw.flush();
            fw.close();

            //java动态编译器
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
            //读取java文件
            Iterable units = fileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask t = compiler.getTask(null,fileManager,null,null,null,units);
            t.call();
            fileManager.close();

            URL[] urls = new URL[]{new URL("file:E:\\code\\chuashen\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.bigone.$Proxy");
            Constructor constructor = clazz.getConstructor(targetInf);
            proxy = constructor.newInstance(target);
        }catch (Exception e){
            e.printStackTrace();
        }

        return proxy;

    }






}
