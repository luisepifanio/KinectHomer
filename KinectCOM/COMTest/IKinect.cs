﻿using System.Collections;

namespace KinectCOM
{
    public interface IKinect
    {
        void Init();
        void Uninit();
        void LearnUser(string name,int skeletonID);
        bool StartTracking(int skeletonID);
        void StopTracking(int skeletonID);

        void GestureRecordCompleted(string gestureName, string ctxt);

        void RecordingCountdownEvent(int p);

        void GestureRecognitionCompleted(string gesture);

        void RecordGesture(string gestureName, string ctxt);

        void RecognizeGesture(string ctxt);

        void StopRecGesture();

        void OnAddOnGestureValueChange(float value);

        void ContextSelected(string ctxt);

        void StoreGestures();

        IEnumerable LoadGestures();
        void SetTrackingStrategy(int strat);
        void TrackingStarted(int matchingSkeleton);
        void TrackingStopped(int matchingSkeleton);
        string GetObjects();
        void UserDetected(User user);
        void UserLost(User user);
        void SetDefaultHand(bool def);
        string LoadUsers();
        void AddUser(string user);
        void DelUser(string user);
        void SetNewObjectContext(string context);
    }
}