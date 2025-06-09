package com.example.demo.proxy;

public class Main {

	public static void main(String[] args) {
		Person man = new Man();
		Person woman = new Woman();
		man.work();
		woman.work();
		
		// 使用代理 proxy
		Person womanProxy = new PersonProxy(woman);
		Person manProxy = new PersonProxy(man);

		womanProxy.work();
		manProxy.work();
		
	}
}
