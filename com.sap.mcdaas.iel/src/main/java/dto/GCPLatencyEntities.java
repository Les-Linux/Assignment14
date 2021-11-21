package dto;

/**
 *  a pojo containing the IEL GCP latency statistic fields
 */
public class GCPLatencyEntities {
    String id;
    String destination;
    String destinationIP;
    String packetLostCount;
    String rttAvg;
    String rttMax;
    String rttMDev;
    String rttMin;
    String source;
    String sourceIP;
    String timestamp; //format 2020-11-22_14:00:05
    String typeOfProtocol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    public String getPacketLostCount() {
        return packetLostCount;
    }

    public void setPacketLostCount(String packetLostCount) {
        this.packetLostCount = packetLostCount;
    }

    public String getRttAvg() {
        return rttAvg;
    }

    public void setRttAvg(String rttAvg) {
        this.rttAvg = rttAvg;
    }

    public String getRttMax() {
        return rttMax;
    }

    public void setRttMax(String rttMax) {
        this.rttMax = rttMax;
    }

    public String getRttMDev() {
        return rttMDev;
    }

    public void setRttMDev(String rttMDev) {
        this.rttMDev = rttMDev;
    }

    public String getRttMin() {
        return rttMin;
    }

    public void setRttMin(String rttMin) {
        this.rttMin = rttMin;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTypeOfProtocol() {
        return typeOfProtocol;
    }

    public void setTypeOfProtocol(String typeOfProtocol) {
        this.typeOfProtocol = typeOfProtocol;
    }
}
