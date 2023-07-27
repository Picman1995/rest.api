package py.edu.ucsa.rest.api.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="cobros_det_concepto")
@NamedQuery(name="CobroDetalleConcepto.findAll", query="SELECT cdc FROM CobroDetalleConcepto cdc")
public class CobroDetalleConcepto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double monto;
	
	@ManyToOne
	@JoinColumn(name="id_concepto")
	private ConceptoCobro concepto;

	public ConceptoCobro getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoCobro concepto) {
		this.concepto = concepto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
