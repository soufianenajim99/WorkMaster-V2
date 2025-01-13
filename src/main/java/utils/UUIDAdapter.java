package utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.UUID;

public class UUIDAdapter extends XmlAdapter<String, UUID> {
    @Override
    public UUID unmarshal(String v) {
        return UUID.fromString(v);
    }

    @Override
    public String marshal(UUID v) {
        return v.toString();
    }
}
