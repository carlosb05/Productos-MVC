
package Modelo;


public class Producto {
    private int proId;
    private String proNombre;
    private String proCodigo;
    private String proDescripcion;
    private String proDimensiones;
    private Float proValor;
    private int catId;
    

    public Producto() {
    }

    public Producto(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    

    public Producto(String proNombre, String proCodigo, String proDescripcion, String proDimensiones, Float proValor, int catId) {
        this.proNombre = proNombre;
        this.proCodigo = proCodigo;
        this.proDescripcion = proDescripcion;
        this.proDimensiones = proDimensiones;
        this.proValor = proValor;
        this.catId = catId;
        
    }

    public Producto(int proId, String proNombre, String proCodigo, String proDescripcion, String proDimensiones, Float proValor, int catId) {
        this.proId = proId;
        this.proNombre = proNombre;
        this.proCodigo = proCodigo;
        this.proDescripcion = proDescripcion;
        this.proDimensiones = proDimensiones;
        this.proValor = proValor;
        this.catId = catId;
        
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProDescripcion() {
        return proDescripcion;
    }

    public void setProDescripcion(String proDescripcion) {
        this.proDescripcion = proDescripcion;
    }

    public String getProDimensiones() {
        return proDimensiones;
    }

    public void setProDimensiones(String proDimensiones) {
        this.proDimensiones = proDimensiones;
    }

    public Float getProValor() {
        return proValor;
    }

    public void setProValor(Float proValor) {
        this.proValor = proValor;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    

    
    
    
    
}
