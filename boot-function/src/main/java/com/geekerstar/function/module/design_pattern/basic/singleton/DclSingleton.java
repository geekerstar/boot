package com.geekerstar.function.module.design_pattern.basic.singleton;

/**
 * @author geekerstar
 * @date 2023/2/19 16:32
 *
 * 为什么使用volatile 修饰了singleton 引用还用synchronized 锁？
 * volatile 只保证了共享变量 singleton 的可见性，但是 singleton = new Singleton(); 这个操作不是原子的，可以分为三步：
 * 步骤1：在堆内存申请一块内存空间；
 * 步骤2：初始化申请好的内存空间；
 * 步骤3：将内存空间的地址赋值给 singleton；
 * 所以singleton = new Singleton(); 是一个由三步操作组成的复合操作，多线程环境下A 线程执行了第一步、第二步之后发生线程切换，B 线程开始执行第一步、第二步、第三步（因为A 线程singleton 是还没有赋值的），所以为了保障这三个步骤不可中断，可以使用synchronized 在这段代码块上加锁
 *
 * 第一次检查singleton 为空后为什么内部还进行第二次检查？
 * A 线程进行判空检查之后开始执行synchronized代码块时发生线程切换(线程切换可能发生在任何时候)，B 线程也进行判空检查，B线程检查 singleton == null 结果为true，也开始执行synchronized代码块，虽然synchronized 会让二个线程串行执行，如果synchronized代码块内部不进行二次判空检查，singleton 可能会初始化二次。
 *
 * volatile 除了内存可见性，还有别的作用吗？
 * volatile 修饰的变量除了可见性，还能防止指令重排序。
 * 指令重排序 是编译器和处理器为了优化程序执行的性能而对指令序列进行重排的一种手段。现象就是CPU 执行指令的顺序可能和程序代码的顺序不一致，例如 a = 1; b = 2; 可能 CPU 先执行b=2; 后执行a=1;
 * singleton = new Singleton(); 由三步操作组合而成，如果不使用volatile 修饰，可能发生指令重排序。步骤3 在步骤2 之前执行，singleton 引用的是还没有被初始化的内存空间，别的线程调用单例的方法就会引发未被初始化的错误。
 *
 * 指令重排序也遵循一定的规则：
 * 重排序不会对存在依赖关系的操作进行重排
 * 重排序目的是优化性能，不管怎样重排，单线程下的程序执行结果不会变
 */
public class DclSingleton {

    /**
     * volatile保证，当uniqueInstance变量被初始化成Singleton实例时，多个线程可以正确处理uniqueInstance变量
     */
    private static volatile DclSingleton uniqueInstance;

    private DclSingleton() {
    }

    public static DclSingleton getInstance() {
        // 检查实例，如果不存在，就进入同步代码块
        if (null == uniqueInstance) {
            // 只有第一次才彻底执行这里的代码
            synchronized (DclSingleton.class) {
                // 进入同步代码块后，再检查一次，如果仍是null，才创建实例
                if (null == uniqueInstance) {
                    uniqueInstance = new DclSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
