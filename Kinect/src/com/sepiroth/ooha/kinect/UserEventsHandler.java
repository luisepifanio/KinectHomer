package com.sepiroth.ooha.kinect;

import kinect.kinectcom.server.IUserEventsServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jniwrapper.Int32;
import com.jniwrapper.win32.automation.types.BStr;
import com.jniwrapper.win32.automation.types.Variant;
import com.jniwrapper.win32.com.server.CoClassMetaInfo;

public class UserEventsHandler extends IUserEventsServer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KinectSensorListener kinectListener;
	private String currentContext = "";
	
	public UserEventsHandler(CoClassMetaInfo classImpl) {
		super(classImpl);
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPresenceDetected(Int32 skelID){
		logger.info("User presence detected with userID: "+skelID);
		//ComponentGateway.Singleton.get().triggerOccured(kinectListener.getSysDeviceID(), KinectSensorComponent.PRESENCE_DETECTED, new String[]{skelID.toString()});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kinectListener.setTracking(skelID);
	}
	
	public void onUserFound(BStr user, Variant confidence, Int32 skelID){
		
	}
	
	@Override
	public void onPresenceLost(Int32 skelID){
		logger.info("User presence lost with userID: "+skelID);
	}
	
	@Override
	public void onGestureRecognitionCompleted(BStr gestureName){
		// TODO refactor for Gesture model.
		if(!gestureName.toString().contains(("__UNKNOWN"))){
			
			kinectListener.fireAction(gestureName.toString(), currentContext);
		
		}else{
			logger.info("Gesture recognition timeout. Please complete in under 3s");
		}	
	}
	
	@Override
	public void onGestureRecordCompleted(BStr gestureName, BStr ctxt){
		logger.info("Gesture recording result: "+gestureName+", context: "+ctxt);
		kinectListener.updateGestureModel();
	}
	
	@Override
	public void onRecordingCountDownEvent(Int32 time){
		logger.info("Recording countdown event received: "+time);
	}

	@Override
	public void onContextSelected(BStr ctxt){
		if(!ctxt.toString().equals("__NOCONTEXT")){
			System.out.println("Context "+ctxt+" selected. Recognition starting.");
			this.currentContext = ctxt.toString();
			kinectListener.recognizeGesture(ctxt);
		}else{
			System.out.println("Context reset");
			this.currentContext = "";
		}
		
		
	}
	
	public void onAddonGestureValueChange(Variant value){
		System.out.println("Value recieved: "+value.getFltVal());
	}
	
	@Override
	public void onVoiceCommandDetected(BStr command){
		
	}
	public void setKinectListener(KinectSensorListener kinectSensorListener) {
		this.kinectListener = kinectSensorListener;
	}
}
