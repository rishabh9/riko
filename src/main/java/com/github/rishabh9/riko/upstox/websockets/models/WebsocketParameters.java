package com.github.rishabh9.riko.upstox.websockets.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class WebsocketParameters {

    private boolean keepalive;

    private long keepaliveInterval;

    private boolean disableNagleAlgorithm;

    private boolean autoAcceptConnections;

    private long keepaliveGracePeriod;

    private long pythonPingInterval;

    private long pythonPingTimeout;

    public boolean isKeepalive() {
        return keepalive;
    }

    public void setKeepalive(boolean keepalive) {
        this.keepalive = keepalive;
    }

    public long getKeepaliveInterval() {
        return keepaliveInterval;
    }

    public void setKeepaliveInterval(long keepaliveInterval) {
        this.keepaliveInterval = keepaliveInterval;
    }

    public boolean isDisableNagleAlgorithm() {
        return disableNagleAlgorithm;
    }

    public void setDisableNagleAlgorithm(boolean disableNagleAlgorithm) {
        this.disableNagleAlgorithm = disableNagleAlgorithm;
    }

    public boolean isAutoAcceptConnections() {
        return autoAcceptConnections;
    }

    public void setAutoAcceptConnections(boolean autoAcceptConnections) {
        this.autoAcceptConnections = autoAcceptConnections;
    }

    public long getKeepaliveGracePeriod() {
        return keepaliveGracePeriod;
    }

    public void setKeepaliveGracePeriod(long keepaliveGracePeriod) {
        this.keepaliveGracePeriod = keepaliveGracePeriod;
    }

    public long getPythonPingInterval() {
        return pythonPingInterval;
    }

    public void setPythonPingInterval(long pythonPingInterval) {
        this.pythonPingInterval = pythonPingInterval;
    }

    public long getPythonPingTimeout() {
        return pythonPingTimeout;
    }

    public void setPythonPingTimeout(long pythonPingTimeout) {
        this.pythonPingTimeout = pythonPingTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebsocketParameters that = (WebsocketParameters) o;
        return keepalive == that.keepalive &&
                keepaliveInterval == that.keepaliveInterval &&
                disableNagleAlgorithm == that.disableNagleAlgorithm &&
                autoAcceptConnections == that.autoAcceptConnections &&
                keepaliveGracePeriod == that.keepaliveGracePeriod &&
                pythonPingInterval == that.pythonPingInterval &&
                pythonPingTimeout == that.pythonPingTimeout;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keepalive, keepaliveInterval, disableNagleAlgorithm,
                autoAcceptConnections, keepaliveGracePeriod, pythonPingInterval, pythonPingTimeout);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("keepalive", keepalive)
                .add("keepaliveInterval", keepaliveInterval)
                .add("disableNagleAlgorithm", disableNagleAlgorithm)
                .add("autoAcceptConnections", autoAcceptConnections)
                .add("keepaliveGracePeriod", keepaliveGracePeriod)
                .add("pythonPingInterval", pythonPingInterval)
                .add("pythonPingTimeout", pythonPingTimeout)
                .toString();
    }
}
