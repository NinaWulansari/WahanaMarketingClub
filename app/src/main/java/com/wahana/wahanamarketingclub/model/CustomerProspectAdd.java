package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerProspectAdd {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cust_code")
    @Expose
    private String custCode;
    @SerializedName("cust_nama")
    @Expose
    private String custNama;
    @SerializedName("cust_pic")
    @Expose
    private String custPic;
    @SerializedName("cust_hp")
    @Expose
    private String custHp;
    @SerializedName("cust_hp_wa")
    @Expose
    private String custHpWa;
    @SerializedName("cust_alamat")
    @Expose
    private String custAlamat;
    @SerializedName("cust_rt")
    @Expose
    private String custRt;
    @SerializedName("cust_rw")
    @Expose
    private String custRw;
    @SerializedName("alamat_lengkap")
    @Expose
    private String alamatLengkap;
    @SerializedName("poscode_id")
    @Expose
    private String poscodeId;
    @SerializedName("pos_kode")
    @Expose
    private String posKode;
    @SerializedName("pos_camat")
    @Expose
    private String posCamat;
    @SerializedName("pos_lurah")
    @Expose
    private String posLurah;
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
    @SerializedName("cust_email")
    @Expose
    private String custEmail;
    @SerializedName("cust_kelamin")
    @Expose
    private String custKelamin;
    @SerializedName("cust_tgl_lahir")
    @Expose
    private String custTglLahir;
    @SerializedName("cust_agama")
    @Expose
    private String custAgama;
    @SerializedName("occupation_id")
    @Expose
    private String occupationId;
    @SerializedName("kerja_kode")
    @Expose
    private String kerjaKode;
    @SerializedName("cust_npwp")
    @Expose
    private String custNpwp;
    @SerializedName("cust_jenis")
    @Expose
    private String custJenis;
    @SerializedName("cust_type")
    @Expose
    private String custType;
    @SerializedName("cust_noktp")
    @Expose
    private String custNoktp;
    @SerializedName("cust_telp_rumah")
    @Expose
    private String custTelpRumah;
    @SerializedName("cust_telp_kantor")
    @Expose
    private String custTelpKantor;
    @SerializedName("cust_fax")
    @Expose
    private String custFax;
    @SerializedName("salesman_id")
    @Expose
    private String salesmanId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("branch_code")
    @Expose
    private String branchCode;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("company_code")
    @Expose
    private String companyCode;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("create_by")
    @Expose
    private String createBy;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modi_by")
    @Expose
    private String modiBy;
    @SerializedName("status_profile")
    @Expose
    private String statusProfile;
    @SerializedName("status_deal")
    @Expose
    private String statusDeal;
    @SerializedName("doc_1")
    @Expose
    private String doc1;
    @SerializedName("doc_2")
    @Expose
    private String doc2;
    @SerializedName("doc_3")
    @Expose
    private String doc3;
    @SerializedName("doc_4")
    @Expose
    private String doc4;
    @SerializedName("doc_5")
    @Expose
    private String doc5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustNama() {
        return custNama;
    }

    public void setCustNama(String custNama) {
        this.custNama = custNama;
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }

    public String getCustAlamat() {
        return custAlamat;
    }

    public void setCustAlamat(String custAlamat) {
        this.custAlamat = custAlamat;
    }

    public String getCustRt() {
        return custRt;
    }

    public void setCustRt(String custRt) {
        this.custRt = custRt;
    }

    public String getCustRw() {
        return custRw;
    }

    public void setCustRw(String custRw) {
        this.custRw = custRw;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getPoscodeId() {
        return poscodeId;
    }

    public void setPoscodeId(String poscodeId) {
        this.poscodeId = poscodeId;
    }

    public String getPosKode() {
        return posKode;
    }

    public void setPosKode(String posKode) {
        this.posKode = posKode;
    }

    public String getPosCamat() {
        return posCamat;
    }

    public void setPosCamat(String posCamat) {
        this.posCamat = posCamat;
    }

    public String getPosLurah() {
        return posLurah;
    }

    public void setPosLurah(String posLurah) {
        this.posLurah = posLurah;
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

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustKelamin() {
        return custKelamin;
    }

    public void setCustKelamin(String custKelamin) {
        this.custKelamin = custKelamin;
    }

    public String getCustTglLahir() {
        return custTglLahir;
    }

    public void setCustTglLahir(String custTglLahir) {
        this.custTglLahir = custTglLahir;
    }

    public String getCustAgama() {
        return custAgama;
    }

    public void setCustAgama(String custAgama) {
        this.custAgama = custAgama;
    }

    public String getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(String occupationId) {
        this.occupationId = occupationId;
    }

    public String getKerjaKode() {
        return kerjaKode;
    }

    public void setKerjaKode(String kerjaKode) {
        this.kerjaKode = kerjaKode;
    }

    public String getCustNpwp() {
        return custNpwp;
    }

    public void setCustNpwp(String custNpwp) {
        this.custNpwp = custNpwp;
    }

    public String getCustJenis() {
        return custJenis;
    }

    public void setCustJenis(String custJenis) {
        this.custJenis = custJenis;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustNoktp() {
        return custNoktp;
    }

    public void setCustNoktp(String custNoktp) {
        this.custNoktp = custNoktp;
    }

    public String getCustTelpRumah() {
        return custTelpRumah;
    }

    public void setCustTelpRumah(String custTelpRumah) {
        this.custTelpRumah = custTelpRumah;
    }

    public String getCustTelpKantor() {
        return custTelpKantor;
    }

    public void setCustTelpKantor(String custTelpKantor) {
        this.custTelpKantor = custTelpKantor;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModiBy() {
        return modiBy;
    }

    public void setModiBy(String modiBy) {
        this.modiBy = modiBy;
    }

    public String getStatusProfile() {
        return statusProfile;
    }

    public void setStatusProfile(String statusProfile) {
        this.statusProfile = statusProfile;
    }

    public String getCustHpWa() {
        return custHpWa;
    }

    public void setCustHpWa(String custHpWa) {
        this.custHpWa = custHpWa;
    }

    public String getStatusDeal() {
        return statusDeal;
    }

    public void setStatusDeal(String statusDeal) {
        this.statusDeal = statusDeal;
    }

    public String getDoc1() {
        return doc1;
    }

    public void setDoc1(String doc1) {
        this.doc1 = doc1;
    }

    public String getDoc2() {
        return doc2;
    }

    public void setDoc2(String doc2) {
        this.doc2 = doc2;
    }

    public String getDoc3() {
        return doc3;
    }

    public void setDoc3(String doc3) {
        this.doc3 = doc3;
    }

    public String getDoc4() {
        return doc4;
    }

    public void setDoc4(String doc4) {
        this.doc4 = doc4;
    }

    public String getDoc5() {
        return doc5;
    }

    public void setDoc5(String doc5) {
        this.doc5 = doc5;
    }
}
