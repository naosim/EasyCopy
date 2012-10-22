package com.naosim.main;

import java.util.concurrent.CountDownLatch;

import junit.framework.Assert;

import org.junit.Test;


public class MainTest {

	@Test
	public void test_Main_normal() {
		// 入力値
		String[] args = { "value" };
		Main main = new Main(args);

		// クリップボードにコピーする部分のモックを作成
		final CountDownLatch sig = new CountDownLatch(1);
		StringSaveableMock stringSaveableMock = new StringSaveableMock();
		stringSaveableMock.setCountDownLatch(sig);
		stringSaveableMock.setValueAssert("valueが保存されること", "value");
		main.stringSaveable = stringSaveableMock;

		// 実行
		main.run();

		Assert.assertEquals("保存が呼ばれていること", 0, sig.getCount());
	}

	@Test
	public void test_Main_null() {
		String[] args = null;
		Main main = new Main(args);

		// クリップボードにコピーする部分のモックを作成
		final CountDownLatch sig = new CountDownLatch(1);
		StringSaveableMock stringSaveableMock = new StringSaveableMock();
		stringSaveableMock.setCountDownLatch(sig);
		main.stringSaveable = stringSaveableMock;

		// 実行
		main.run();

		Assert.assertEquals("保存が呼ばれないこと", 1, sig.getCount());
	}

	public static class StringSaveableMock implements StringSaveable {

		private String message;
		private String exp;
		private CountDownLatch sig;

		@Override
		public void save(String value) {
			Assert.assertEquals(message, exp, value);

			if (sig != null) {
				sig.countDown();
			}

		}

		public void setValueAssert(String message, String exp) {
			this.message = message;
			this.exp = exp;
		}

		public void setCountDownLatch(CountDownLatch sig) {
			this.sig = sig;
		}

	}

}
