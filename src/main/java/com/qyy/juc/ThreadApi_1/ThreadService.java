package com.qyy.juc.ThreadApi_1;

/**
 * @author qyhsmx@outlook.com
 * @date
 */
public class ThreadService {

    private static Thread thread;
    private static Thread exe;
    private static boolean flag = Boolean.FALSE;
    //thread executor
    public void exec(Runnable runnable){
        //use daemon thread to shutdown thread
        thread = new Thread(() -> {
            try {
                exe = new Thread(runnable);
                exe.setDaemon(true);
                exe.join();
                exe.start();
                //we can define a flag to shutdown daemon thread that have done
                flag = Boolean.TRUE;
                System.out.println("守护线程结束");
            } catch (InterruptedException e) {
                System.out.println("线程被中断");
            }
        });
        thread.start();
    }


    // shutdown method
    public void shutdown(Long mills){
        //shutdown when daemon thread exec timeout
        while (!flag){
            long start = System.currentTimeMillis();
            //just need to shutdown main thread
            if(System.currentTimeMillis()-start>mills)
                    thread.interrupt();
            System.out.println("关闭守护线程");

        }
        flag = Boolean.FALSE;
    }
}
