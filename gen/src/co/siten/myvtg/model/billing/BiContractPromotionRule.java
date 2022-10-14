package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BI_CONTRACT_PROMOTION_RULE database table.
 * 
 */
@Entity
@Table(name="BI_CONTRACT_PROMOTION_RULE")
@NamedQuery(name="BiContractPromotionRule.findAll", query="SELECT b FROM BiContractPromotionRule b")
public class BiContractPromotionRule implements Serializable {
	private static final long serialVersionUID = 1L;

	private String active;

	private String description;

	@Column(name="RULE_CODE")
	private String ruleCode;

	@Column(name="RULE_ID")
	private BigDecimal ruleId;

	@Column(name="RULE_NAME")
	private String ruleName;

	public BiContractPromotionRule() {
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuleCode() {
		return this.ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public BigDecimal getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}