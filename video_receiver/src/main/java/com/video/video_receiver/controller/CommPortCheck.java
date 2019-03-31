//package com.video.video_receiver.controller;
//
//import gnu.io.*;
//import java.util.Enumeration;
//
///**
// * checks if can read from RS232 for s port
// */
//public class CommPortCheck {
//
//    public static void main(String[] args) {
//		System.out.println("Program Started!!!");
//
//		CommPortIdentifier serialPortId;
//
//		Enumeration enumComm;
//
//		enumComm = CommPortIdentifier.getPortIdentifiers();
//
//		while(enumComm.hasMoreElements())
//		{
//			serialPortId = (CommPortIdentifier)enumComm.nextElement();
//			if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL)
//			{
//				System.out.println(serialPortId.getName());
//			}
//		}
//
//		System.out.println("Program Finished Sucessfully");
//	}
//
//}