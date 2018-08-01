
/*
* 文件名：TestRxJava.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年11月29日
* 修改内容：
*/

package com.ysepay.test.rxJava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class TestRxJava {

	public static void main(String[] args) throws InterruptedException {
		Observable<Long> myObservable = Observable.interval(2,
				TimeUnit.SECONDS);

		Subscriber<Long> mySubscriber = new Subscriber<Long>() {
			@Override
			public void onNext(Long s) {
				System.out.println("onNext................." + s);
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted.................");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError....................");
			}
		};

		myObservable.subscribe(mySubscriber);
		
		Thread.currentThread().sleep(10000l);
	}

}
