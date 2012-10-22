package com.naosim.main;

public class Main implements Runnable {

	public static void main(String[] args) {
		Runnable main = new Main(args);
		main.run();
	}

	private String args[];
	public StringSaveable stringSaveable;

	public Main(String[] args) {
		this.args = args;
		stringSaveable = new ClipbordSaver();
	}

	@Override
	public void run() {
		String value = getValue(args);
		if (value == null) {
			return;
		}

		stringSaveable.save(value);
		
	}

	private String getValue(String[] args) {
		if (args == null || args.length == 0) {
			return null;
		}
		return args[0];
	}
}
