
package com.androdevlinux.percy.defi.models.wazirx;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Npxsinr {

    @SerializedName("base_unit")
    @Expose
    private String baseUnit;
    @SerializedName("quote_unit")
    @Expose
    private String quoteUnit;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("open")
    @Expose
    private double open;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("sell")
    @Expose
    private String sell;
    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("at")
    @Expose
    private int at;
    @SerializedName("name")
    @Expose
    private String name;

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public Npxsinr withBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
        return this;
    }

    public String getQuoteUnit() {
        return quoteUnit;
    }

    public void setQuoteUnit(String quoteUnit) {
        this.quoteUnit = quoteUnit;
    }

    public Npxsinr withQuoteUnit(String quoteUnit) {
        this.quoteUnit = quoteUnit;
        return this;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public Npxsinr withLow(String low) {
        this.low = low;
        return this;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Npxsinr withHigh(String high) {
        this.high = high;
        return this;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Npxsinr withLast(String last) {
        this.last = last;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Npxsinr withType(String type) {
        this.type = type;
        return this;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public Npxsinr withOpen(double open) {
        this.open = open;
        return this;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Npxsinr withVolume(String volume) {
        this.volume = volume;
        return this;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public Npxsinr withSell(String sell) {
        this.sell = sell;
        return this;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public Npxsinr withBuy(String buy) {
        this.buy = buy;
        return this;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public Npxsinr withAt(int at) {
        this.at = at;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Npxsinr withName(String name) {
        this.name = name;
        return this;
    }

}
