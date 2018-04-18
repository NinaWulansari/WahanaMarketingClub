package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterPosCode {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pos_kode")
    @Expose
    private String posKode;
    @SerializedName("pos_lurah")
    @Expose
    private String posLurah;
    @SerializedName("pos_camat")
    @Expose
    private String posCamat;
    @SerializedName("pos_kota")
    @Expose
    private String posKota;
    @SerializedName("pos_prop")
    @Expose
    private String posProp;
    @SerializedName("kota_id")
    @Expose
    private String kotaId;
    @SerializedName("propinsi_id")
    @Expose
    private String propinsiId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosKode() {
        return posKode;
    }

    public void setPosKode(String posKode) {
        this.posKode = posKode;
    }

    public String getPosLurah() {
        return posLurah;
    }

    public void setPosLurah(String posLurah) {
        this.posLurah = posLurah;
    }

    public String getPosCamat() {
        return posCamat;
    }

    public void setPosCamat(String posCamat) {
        this.posCamat = posCamat;
    }

    public String getPosKota() {
        return posKota;
    }

    public void setPosKota(String posKota) {
        this.posKota = posKota;
    }

    public String getPosProp() {
        return posProp;
    }

    public void setPosProp(String posProp) {
        this.posProp = posProp;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getPropinsiId() {
        return propinsiId;
    }

    public void setPropinsiId(String propinsiId) {
        this.propinsiId = propinsiId;
    }

}
