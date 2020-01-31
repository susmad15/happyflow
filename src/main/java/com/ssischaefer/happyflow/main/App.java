package com.ssischaefer.happyflow.main;

import com.ssischaefer.happyflow.view.HappyFlowGUI;

public class App {

	
	// Start program
	public static void main(String args[]) {
		try {
			HappyFlowGUI gui = new HappyFlowGUI();
			gui.openShell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
