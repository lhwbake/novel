package com.bake.novel.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class KeyUtil {

	public static String generatorUUID() {
		TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		return timeBasedGenerator.generate().toString();
	}
	
}
