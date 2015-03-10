package org.eyalgo.morphia;

public enum MyEnum {
	AEnum {

		@Override
		public void foo() {
		}
	},
	BEnum {
		@Override
		public void foo() {
		}
	};
	
	public abstract void foo();
}
