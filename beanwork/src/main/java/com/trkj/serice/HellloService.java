package com.trkj.serice;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class HellloService {

    private  int hid;
    public void printInfo(String name){
        log.debug("欢迎您:{}",name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HellloService that = (HellloService) object;
        return hid == that.hid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hid);
    }
}
