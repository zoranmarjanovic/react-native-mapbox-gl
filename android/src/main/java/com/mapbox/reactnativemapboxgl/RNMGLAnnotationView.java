package com.mapbox.reactnativemapboxgl;

import android.content.Context;
import android.view.MotionEvent;

import com.facebook.react.views.view.ReactViewGroup;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.HashSet;
import java.util.Set;

public class RNMGLAnnotationView extends ReactViewGroup {

    private Set<PropertyListener> propertyListeners;
    private String annotationId;
    private LatLng coordinate;
    private float anchorV;
    private float anchorU;
    private float layoutWidth;
    private float layoutHeight;
    private boolean passThroughTouchEvents;

    public RNMGLAnnotationView(Context context) {
        super(context);
        this.propertyListeners = new HashSet<>();
    }

    // Properties

    public String getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(String annotationId) {
        this.annotationId = annotationId;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
        fireUpdateEvent();
    }

    // React layout

    public void setLayoutDimensions(float layoutWidth, float layoutHeight) {
        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
    }

    public float getLayoutWidth() {
        return layoutWidth;
    }

    public float getLayoutHeight() {
        return layoutHeight;
    }

    // Listeners

    public void addPropertyListener(PropertyListener propertyListener) {
        propertyListeners.add(propertyListener);
    }

    public void removePropertyListener(PropertyListener propertyListener) {
        propertyListeners.remove(propertyListener);
    }

    private void fireUpdateEvent() {
        for (PropertyListener listener : propertyListeners) {
            listener.propertiesUpdated(this);
        }
    }

    public interface PropertyListener {
        void propertiesUpdated(RNMGLAnnotationView view);
    }

    public void setAnchorV(float anchorV) {
        this.anchorV = anchorV;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public float getAnchorU() {
        return anchorU;
    }

    public void setAnchorU(float anchorU) {
        this.anchorU = anchorU;
    }
    public void passThroughTouchEvents(boolean value) { passThroughTouchEvents = value; }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        return passThroughTouchEvents ? false : result;
    }
}
