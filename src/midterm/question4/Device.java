package org.howard.edu.lsp.midterm.question4;

public abstract class Device {
    private final String id;
    private final String location;
    private boolean connected = false;

    protected Device(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    protected void setConnected(boolean value) {
        this.connected = value;
    }

    public boolean isConnected() {
        return connected;
    }

    public abstract String getDeviceType();
    public abstract String getStatus();
}