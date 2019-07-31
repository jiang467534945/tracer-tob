package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

/**
 * Explain:     [实体类]
 * Date:        [2019-05-28 11:05:44]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("num_code")
public class NumCode extends BaseDTO<NumCode> {

    private int numberCode;

    private String advantage;

    private String shortcoming;

    private String proposal;

    private int type;
    private String parent;


    private String talent;

    private String charactert;
    private String marriage;
    private String parenting;
    private String cause;
    private String connections;
    private String healthy;
    private String grow;

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getCharactert() {
        return charactert;
    }

    public void setCharactert(String charactert) {
        this.charactert = charactert;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getParenting() {
        return parenting;
    }

    public void setParenting(String parenting) {
        this.parenting = parenting;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getGrow() {
        return grow;
    }

    public void setGrow(String grow) {
        this.grow = grow;
    }

    public int getNumberCode() {
        return this.numberCode;
    }

    public void setNumberCode(int numberCode) {
        this.numberCode = numberCode;
    }

    public String getAdvantage() {
        return this.advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getShortcoming() {
        return this.shortcoming;
    }

    public void setShortcoming(String shortcoming) {
        this.shortcoming = shortcoming;
    }

    public String getProposal() {
        return this.proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("NumCode{\n");
        strBuf.append("numberCode:").append(this.numberCode).append(",\n");
        strBuf.append("advantage:").append(this.advantage).append(",\n");
        strBuf.append("shortcoming:").append(this.shortcoming).append(",\n");
        strBuf.append("proposal:").append(this.proposal).append(",\n");
        strBuf.append("type:").append(this.type).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
