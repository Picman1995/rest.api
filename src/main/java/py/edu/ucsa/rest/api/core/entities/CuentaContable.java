package py.edu.ucsa.rest.api.core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * La clase de persistencia para la tabla cuentas_contables 
 * @author pablo
 */
@Entity
@Table(name="cuentas_contables")
@NamedQuery(name="CuentaContable.findAll", query="SELECT c FROM CuentaContable c")
public class CuentaContable implements Serializable {

	private static final long serialVersionUID = -860003248925678471L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String codigo;

	private String descripcion;
	
	private Integer nivel;
	
	@Column(name="tipo_cuenta", nullable=false)
	private String tipoCuenta;  //A: ACTIVO, P: PASIVO, D: DEUDORA, H: ACREEDORA
	
	@Column(name="numero_cuenta") 
	private String numeroCuenta;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_cuenta_padre")
    private CuentaContable cuentaPadre;
	
    @OneToMany(mappedBy="cuentaPadre", cascade=CascadeType.PERSIST)
    private List<CuentaContable> cuentasHijas;
	
	private Boolean asentable;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
//
//	public CuentaContable getCuentaPadre() {
//		return cuentaPadre;
//	}
//
//	public void setCuentaPadre(CuentaContable cuentaPadre) {
//		this.cuentaPadre = cuentaPadre;
//	}

	public Boolean getAsentable() {
		return asentable;
	}

	public void setAsentable(Boolean asentable) {
		this.asentable = asentable;
	}

	public CuentaContable() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
//	public List<CuentaContable> getCuentasHijas() {
//		return cuentasHijas;
//	}
//	
//	public void setCuentasHijas(List<CuentaContable> cuentasHijas) {
//		this.cuentasHijas = cuentasHijas;
//	}

}