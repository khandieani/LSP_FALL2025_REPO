package org.howard.edu.lsp.midterm.question4;

public class Doorlock extends Device implements Networked, BatteryPowered {
  private int batteryPercent;

  public Doorlock(String id, String location, int initialBattery) {
    super(id, location);
    if (initialBattery < 0 || initialBattery > 100) {
      throw new IllegalArgumentException("battery 0..100");
    }
    this.batteryPercent = initialBattery;
  }

  // Networked
  @Override
  public void connect() {
    setConnected(true);
  }

  @Override
  public void disconnect() {
    setConnected(false);
  }

  @Override
  public boolean isConnected() {
    return super.isConnected();
  }

  // BatteryPowered
  @Override
  public int getBatteryPercent() {
    return batteryPercent;
  }

  @Override
  public void setBatteryPercent(int percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("battery 0..100");
    }
    this.batteryPercent = percent;
  }

  // Status
  @Override
  public String getStatus() {
    String connStatus = isConnected() ? "up" : "down";
    return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
           ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
  }

  @Override
  public String getDeviceType() {
    return "DoorLock";
  }
}
