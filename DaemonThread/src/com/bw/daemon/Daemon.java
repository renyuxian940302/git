package com.bw.daemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * <p>Description: </p>
 * @Auther 任育贤
 * @date 创建时间 2018年6月16日下午3:46:45
 * @Version 1.0
 */
class DaemonThread implements Runnable{
	@Override
	public void run() {
		System.out.println("进入了守护线程"+Thread.currentThread().getName());
		try {
			writeToFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("退出了守护线程"+Thread.currentThread().getName());
		
	}
	private void writeToFile() throws Exception{
		File fileName = new File("D:"+File.separator+"daemon.txt");
		OutputStream os = new FileOutputStream(fileName,true);
		int count = 0;
		while (count<999) {
			os.write(("\r\nword"+count).getBytes());
			System.out.println("守护线程"+Thread.currentThread().getName()+"项文件写入了Word"+count++);
			Thread.sleep(1000);
		}
	}
}

public class Daemon {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				System.out.println("进入主线程"+Thread.currentThread().getName());
				DaemonThread daemonThread = new DaemonThread();
				Thread thread = new Thread(daemonThread);
				//设置守护线程
				thread.setDaemon(true);
				thread.start();
				Scanner sc = new Scanner(System.in);
				sc.next();
				System.out.println("退出主线程"+Thread.currentThread().getName());
	}
}
