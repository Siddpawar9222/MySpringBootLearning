package com.start.Demo.BeamConfig;

import org.springframework.stereotype.Component;

@Component
public class Snapdragon implements MobileProcessor {

	@Override
	public void process() {
       System.out.println("World best Mobile Proccessor....");
		
	}

}
