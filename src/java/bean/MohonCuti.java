/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author USER
 */
public class MohonCuti {
    private int id;
    private String tarikhMula;
    private String tarikhTamat;
    private String tarikhMohon;
    private String alamatCuti;
    private String catatan;
    private int bilanganCuti;
    private int id_staff;
    private int id_sokonglulus;
    private String status;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tarikhMula
     */
    public String getTarikhMula() {
        return tarikhMula;
    }

    /**
     * @param tarikhMula the tarikhMula to set
     */
    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    /**
     * @return the tarikhTamat
     */
    public String getTarikhTamat() {
        return tarikhTamat;
    }

    /**
     * @param tarikhTamat the tarikhTamat to set
     */
    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    /**
     * @return the tarikhMohon
     */
    public String getTarikhMohon() {
        return tarikhMohon;
    }

    /**
     * @param tarikhMohon the tarikhMohon to set
     */
    public void setTarikhMohon(String tarikhMohon) {
        this.tarikhMohon = tarikhMohon;
    }

    /**
     * @return the alamatCuti
     */
    public String getAlamatCuti() {
        return alamatCuti;
    }

    /**
     * @param alamatCuti the alamatCuti to set
     */
    public void setAlamatCuti(String alamatCuti) {
        this.alamatCuti = alamatCuti;
    }

    /**
     * @return the catatan
     */
    public String getCatatan() {
        return catatan;
    }

    /**
     * @param catatan the catatan to set
     */
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    /**
     * @return the bilanganCuti
     */
    public int getBilanganCuti() {
        return bilanganCuti;
    }

    /**
     * @param bilanganCuti the bilanganCuti to set
     */
    public void setBilanganCuti(int bilanganCuti) {
        this.bilanganCuti = bilanganCuti;
    }

    /**
     * @return the id_sokonglulus
     */
    public int getId_sokonglulus() {
        return id_sokonglulus;
    }

    /**
     * @param id_sokonglulus the id_sokonglulus to set
     */
    public void setId_sokonglulus(int id_sokonglulus) {
        this.id_sokonglulus = id_sokonglulus;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
}
