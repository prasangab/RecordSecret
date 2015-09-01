package com.example.prasanga.recordsecret;

/**
 * Created by Prasanga on 9/1/2015.
 */
public class SlashDetector {

    private long maxDelay = 25;
    private long interPeakWait = 300;

    private long lastPeak;
    private long lastAccelerationPeakTime;
    private long lastAudioPeakTime;

    private float accelerationThresold = 0.7f;
    public float audioThresold = 0.5f;

    public boolean AddAccelerationPeak(float acceleration)
    {
        if(acceleration < accelerationThresold)
        {
            return false;
        }

        lastAccelerationPeakTime = System.currentTimeMillis();
        return ComparePeaks();
    }

    public boolean AddAudioPeak(float audio)
    {
        if(audio < audioThresold)
        {
            return false;
        }

        lastAudioPeakTime = System.currentTimeMillis();
        return ComparePeaks();
    }

    public boolean ComparePeaks()
    {
        if(Math.abs(lastPeak - System.currentTimeMillis()) > interPeakWait
                &&  Math.abs(lastAccelerationPeakTime - lastAudioPeakTime) < maxDelay)
        {
            lastPeak = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
