package com.placementtracker.placement_tracker.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super ( message );
    }
}
