package com.sepiroth.ooha.kinect.events;

import kinect.kinectcom.server.IUserEventsServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.stir.cs.homer.homerFrameworkAPI.componentUtils.ComponentGateway;

import com.jniwrapper.Int32;
import com.jniwrapper.win32.automation.types.BStr;
import com.jniwrapper.win32.automation.types.Variant;
import com.jniwrapper.win32.com.server.CoClassMetaInfo;
import com.sepiroth.ooha.kinect.KinectSensorComponent;
import com.sepiroth.ooha.kinect.KinectSensorListener;

public class UserEventsHandler extends IUserEventsServer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KinectSensorListener kinectListener;
	private String currentContext = "";
	
	public UserEventsHandler(CoClassMetaInfo classImpl) {
		super(classImpl);
	}
	
	@Override
	public void onPresenceDetected(Int32 skelID){
		logger.info("User presence detected with userID: "+skelID);
		if(kinectListener.getSysDeviceID() != null)
			ComponentGateway.Singleton.get().triggerOccured(kinectListener.getSysDeviceID(), KinectSensorComponent.PRESENCE_DETECTED, new String[]{skelID.toString()});
	}
	
	@Override
	public void onTrackingStarted(Int32 skelID){
		logger.info("Started tracking user with id: "+skelID);
	}
	
	@Override
	public void onTrackingStopped(Int32 skelID){
		logger.info("Stopped tracking user with id: "+skelID);
	}
	
		
	public void onUserFound(BStr user, BStr confidence, Int32 skelID){
		logger.info("User detected: "+user.toString()+","+confidence.toString()+","+skelID.toString());
		if(kinectListener.getSysDeviceID() != null)
			ComponentGateway.Singleton.get().triggerOccured(kinectListener.getSysDeviceID(),KinectSensorComponent.USER_DETECTED , new String[]{user.toString(),confidence.toString(),skelID.toString()});
	}
	
	@Override
	public void onPresenceLost(Int32 skelID){
		logger.info("User presence lost with userID: "+skelID);
		if(kinectListener.getSysDeviceID() != null)
			ComponentGateway.Singleton.get().triggerOccured(kinectListener.getSysDeviceID(),KinectSensorComponent.PRESENCE_LOST , new String[]{skelID.toString()});
	}
	
	@Override
	public void onGestureRecognitionCompleted(BStr gestureName){
		// TODO refactor for Gesture model.
		if(!gestureName.toString().contains(("__UNKNOWN"))){
			
			kinectListener.fireAction(gestureName.toString(), currentContext);
		
		}else{
			logger.info("Gesture not recognized.");
		}	
	}
	
	public void onUserLost(BStr name){
		logger.info("User "+name+" lost.");
		if(kinectListener.getSysDeviceID() != null)
			ComponentGateway.Singleton.get().triggerOccured(kinectListener.getSysDeviceID(),KinectSensorComponent.USER_LOST , new String[]{name.toString()});
	}
	
	@Override
	public void onGestureRecordCompleted(BStr gestureName, BStr ctxt){
		logger.info("Gesture recording completed for Gesture: "+gestureName+" and Context: "+ctxt);
		kinectListener.updateGestureModel();
	}
	
	@Override
	public void onRecordingCountDownEvent(Int32 time){
		logger.info("Recording countdown event received: "+time);
	}

	@Override
	public void onContextSelected(BStr ctxt){
		if(!ctxt.toString().equals("__NOCONTEXT")){
			logger.info("Context selected "+ctxt.toString()+", starting gesture recognition.");
			this.currentContext = ctxt.toString();
			kinectListener.recognizeGesture(ctxt);
		}else{
			logger.info("Context reset.");
			this.currentContext = "";
		}
		
		
	}
	
	public void onAddonGestureValueChange(Variant value){
		System.out.println("Value recieved: "+value.getFltVal());
	}
	
	@Override
	public void onVoiceCommandDetected(BStr command){
		logger.info("Voice command received" + command.toString());
	}
	
	public void setKinectListener(KinectSensorListener kinectSensorListener) {
		this.kinectListener = kinectSensorListener;
	}
}
