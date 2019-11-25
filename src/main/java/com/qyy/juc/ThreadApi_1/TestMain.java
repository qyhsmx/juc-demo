package com.qyy.juc.ThreadApi_1;

/**
 * @author qyhsmx@outlook.com
 * @date
 */
public class TestMain{
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        service.exec(()->{
            try {
                Thread.sleep(10*1000);
//                while (true){
//
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown(5*1000L);
    }
}
