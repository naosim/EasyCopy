package com.naosim.main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipbordSaver implements StringSaveable {

	@Override
	public void save(String value) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(value);
		clipboard.setContents(selection, null);

	}

}