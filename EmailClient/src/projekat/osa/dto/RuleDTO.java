package projekat.osa.dto;

import java.io.Serializable;


import projekat.osa.entity.Rule;

public class RuleDTO implements Serializable {
    
	private Integer id;
    private ConditionDTO condition;
    private OperationDTO operation;

    public RuleDTO() {
    }

    public RuleDTO(Integer id, ConditionDTO condition, OperationDTO operation) {
        this.id = id;
        this.condition = condition;
        this.operation = operation;
    }
    
    public RuleDTO(Rule r) {
        this.id = r.getId();
        this.condition = ConditionDTO.valueOf(r.getCondition().toString());
        this.operation = OperationDTO.valueOf(r.getOperation().toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(ConditionDTO condition) {
        this.condition = condition;
    }

    public OperationDTO getOperation() {
        return operation;
    }

    public void setOperation(OperationDTO operation) {
        this.operation = operation;
    }
    
}
