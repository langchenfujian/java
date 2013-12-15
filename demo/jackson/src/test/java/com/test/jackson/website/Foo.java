package com.test.jackson.website;

public class Foo {
	private String foo;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	@Override
	public String toString() {
		return "Foo [foo=" + foo + "]";
	}
	
}
