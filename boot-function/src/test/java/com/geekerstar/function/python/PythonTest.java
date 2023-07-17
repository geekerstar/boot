package com.geekerstar.function.python;

import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author geekerstar
 * @date 2023/7/17 11:08
 */
public class PythonTest {

    /**
     * 在java类中直接执行python语句
     */
    @Test
    public void test1() {
        //首先调用python的解释器
        PythonInterpreter interpreter = new PythonInterpreter();
        //选择执行的的Python语句
        interpreter.exec("a='hello world'; ");
        interpreter.exec("print a;");
    }

    /**
     * 在java中直接调用python脚本
     */
    @Test
    public void test2() {
        PythonInterpreter interpreter = new PythonInterpreter();
        //我在这里使用相对路径，注意区分
        interpreter.execfile("src/main/resources/python/test1.py");
    }

    /**
     * 使用Runtime.getRuntime()执行python脚本文件
     */
    @Test
    public void test3() {
        Process proc;
        try {
            String[] command = new String[]{"D:\\software\\anaconda3\\python.exe", "D:\\code\\java\\boot\\boot-function\\src\\main\\resources\\python\\test1.py"};
            proc = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test4(){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/resources/python/test2.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("调用函数后的结果：" + pyobj);
    }
}
