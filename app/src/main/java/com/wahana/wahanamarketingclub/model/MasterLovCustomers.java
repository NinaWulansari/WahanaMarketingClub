package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterLovCustomers {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cust_code")
    @Expose
    private String custCode;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("cust_nama")
    @Expose
    private String custNama;
    @SerializedName("cust_pic")
    @Expose
    private String custPic;
    @SerializedName("cust_org")
    @Expose
    private Object custOrg;
    @SerializedName("cust_noktp")
    @Expose
    private String custNoktp;
    @SerializedName("cust_alamat")
    @Expose
    private String custAlamat;
    @SerializedName("noktp_stnk")
    @Expose
    private String noktpStnk;
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
    @SerializedName("alamat_invoice")
    @Expose
    private String alamatInvoice;
    @SerializedName("alamat_kirim")
    @Expose
    private String alamatKirim;
    @SerializedName("cust_telp_rumah")
    @Expose
    private String custTelpRumah;
    @SerializedName("cust_telp_kantor")
    @Expose
    private String custTelpKantor;
    @SerializedName("cust_fax")
    @Expose
    private String custFax;
    @SerializedName("cust_hp")
    @Expose
    private String custHp;
    @SerializedName("cust_email")
    @Expose
    private String custEmail;
    @SerializedName("cust_tgl_lahir")
    @Expose
    private String custTglLahir;
    @SerializedName("cust_agama")
    @Expose
    private String custAgama;
    @SerializedName("accupation_id")
    @Expose
    private Object accupationId;
    @SerializedName("kerja_kode")
    @Expose
    private String kerjaKode;
    @SerializedName("cust_kelamin")
    @Expose
    private String custKelamin;
    @SerializedName("cust_jenis")
    @Expose
    private String custJenis;
    @SerializedName("cust_npwp")
    @Expose
    private String custNpwp;
    @SerializedName("salesman_id")
    @Expose
    private String salesmanId;
    @SerializedName("assigned_to")
    @Expose
    private String assignedTo;
    @SerializedName("cust_sumber")
    @Expose
    private Object custSumber;
    @SerializedName("cust_npk")
    @Expose
    private Object custNpk;
    @SerializedName("cust_kendaraan")
    @Expose
    private Object custKendaraan;
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
    @SerializedName("cust_type")
    @Expose
    private String custType;
    @SerializedName("create_by")
    @Expose
    private int createBy;
    @SerializedName("modi_by")
    @Expose
    private int modiBy;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("kunci")
    @Expose
    private String kunci;
    @SerializedName("kota_id")
    @Expose
    private String kotaId;
    @SerializedName("propinsi_id")
    @Expose
    private String propinsiId;
    @SerializedName("nama_stnk")
    @Expose
    private String namaStnk;
    @SerializedName("alamat_stnk")
    @Expose
    private String alamatStnk;
    @SerializedName("alamat_lengkap")
    @Expose
    private String alamatLengkap;
    @SerializedName("npwp_stnk")
    @Expose
    private String npwpStnk;
    @SerializedName("cust_alamat2")
    @Expose
    private String custAlamat2;
    @SerializedName("cek_alamat_kirim")
    @Expose
    private int cekAlamatKirim;
    @SerializedName("pos_kota2")
    @Expose
    private String posKota2;
    @SerializedName("pos_prop2")
    @Expose
    private String posProp2;
    @SerializedName("propinsi_id2")
    @Expose
    private String propinsiId2;
    @SerializedName("pos_lurah2")
    @Expose
    private String posLurah2;
    @SerializedName("pos_kode2")
    @Expose
    private String posKode2;
    @SerializedName("kota_id2")
    @Expose
    private String kotaId2;
    @SerializedName("pos_camat2")
    @Expose
    private String posCamat2;
    @SerializedName("lead_code")
    @Expose
    private Object leadCode;
    @SerializedName("lead_id")
    @Expose
    private Object leadId;
    @SerializedName("cust_rt")
    @Expose
    private String custRt;
    @SerializedName("cust_rw")
    @Expose
    private String custRw;
    @SerializedName("dealer_id")
    @Expose
    private String dealerId;
    @SerializedName("dealer_code")
    @Expose
    private String dealerCode;
    @SerializedName("rt_stnk")
    @Expose
    private String rtStnk;
    @SerializedName("rw_stnk")
    @Expose
    private String rwStnk;

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

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
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

    public Object getCustOrg() {
        return custOrg;
    }

    public void setCustOrg(Object custOrg) {
        this.custOrg = custOrg;
    }

    public String getCustNoktp() {
        return custNoktp;
    }

    public void setCustNoktp(String custNoktp) {
        this.custNoktp = custNoktp;
    }

    public String getCustAlamat() {
        return custAlamat;
    }

    public void setCustAlamat(String custAlamat) {
        this.custAlamat = custAlamat;
    }

    public String getNoktpStnk() {
        return noktpStnk;
    }

    public void setNoktpStnk(String noktpStnk) {
        this.noktpStnk = noktpStnk;
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

    public String getAlamatInvoice() {
        return alamatInvoice;
    }

    public void setAlamatInvoice(String alamatInvoice) {
        this.alamatInvoice = alamatInvoice;
    }

    public String getAlamatKirim() {
        return alamatKirim;
    }

    public void setAlamatKirim(String alamatKirim) {
        this.alamatKirim = alamatKirim;
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

    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
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

    public Object getAccupationId() {
        return accupationId;
    }

    public void setAccupationId(Object accupationId) {
        this.accupationId = accupationId;
    }

    public String getKerjaKode() {
        return kerjaKode;
    }

    public void setKerjaKode(String kerjaKode) {
        this.kerjaKode = kerjaKode;
    }

    public String getCustKelamin() {
        return custKelamin;
    }

    public void setCustKelamin(String custKelamin) {
        this.custKelamin = custKelamin;
    }

    public String getCustJenis() {
        return custJenis;
    }

    public void setCustJenis(String custJenis) {
        this.custJenis = custJenis;
    }

    public String getCustNpwp() {
        return custNpwp;
    }

    public void setCustNpwp(String custNpwp) {
        this.custNpwp = custNpwp;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Object getCustSumber() {
        return custSumber;
    }

    public void setCustSumber(Object custSumber) {
        this.custSumber = custSumber;
    }

    public Object getCustNpk() {
        return custNpk;
    }

    public void setCustNpk(Object custNpk) {
        this.custNpk = custNpk;
    }

    public Object getCustKendaraan() {
        return custKendaraan;
    }

    public void setCustKendaraan(Object custKendaraan) {
        this.custKendaraan = custKendaraan;
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

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getModiBy() {
        return modiBy;
    }

    public void setModiBy(int modiBy) {
        this.modiBy = modiBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
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

    public String getNamaStnk() {
        return namaStnk;
    }

    public void setNamaStnk(String namaStnk) {
        this.namaStnk = namaStnk;
    }

    public String getAlamatStnk() {
        return alamatStnk;
    }

    public void setAlamatStnk(String alamatStnk) {
        this.alamatStnk = alamatStnk;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getNpwpStnk() {
        return npwpStnk;
    }

    public void setNpwpStnk(String npwpStnk) {
        this.npwpStnk = npwpStnk;
    }

    public String getCustAlamat2() {
        return custAlamat2;
    }

    public void setCustAlamat2(String custAlamat2) {
        this.custAlamat2 = custAlamat2;
    }

    public int getCekAlamatKirim() {
        return cekAlamatKirim;
    }

    public void setCekAlamatKirim(int cekAlamatKirim) {
        this.cekAlamatKirim = cekAlamatKirim;
    }

    public String getPosKota2() {
        return posKota2;
    }

    public void setPosKota2(String posKota2) {
        this.posKota2 = posKota2;
    }

    public String getPosProp2() {
        return posProp2;
    }

    public void setPosProp2(String posProp2) {
        this.posProp2 = posProp2;
    }

    public String getPropinsiId2() {
        return propinsiId2;
    }

    public void setPropinsiId2(String propinsiId2) {
        this.propinsiId2 = propinsiId2;
    }

    public String getPosLurah2() {
        return posLurah2;
    }

    public void setPosLurah2(String posLurah2) {
        this.posLurah2 = posLurah2;
    }

    public String getPosKode2() {
        return posKode2;
    }

    public void setPosKode2(String posKode2) {
        this.posKode2 = posKode2;
    }

    public String getKotaId2() {
        return kotaId2;
    }

    public void setKotaId2(String kotaId2) {
        this.kotaId2 = kotaId2;
    }

    public String getPosCamat2() {
        return posCamat2;
    }

    public void setPosCamat2(String posCamat2) {
        this.posCamat2 = posCamat2;
    }

    public Object getLeadCode() {
        return leadCode;
    }

    public void setLeadCode(Object leadCode) {
        this.leadCode = leadCode;
    }

    public Object getLeadId() {
        return leadId;
    }

    public void setLeadId(Object leadId) {
        this.leadId = leadId;
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

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getRtStnk() {
        return rtStnk;
    }

    public void setRtStnk(String rtStnk) {
        this.rtStnk = rtStnk;
    }

    public String getRwStnk() {
        return rwStnk;
    }

    public void setRwStnk(String rwStnk) {
        this.rwStnk = rwStnk;
    }

}