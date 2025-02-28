package com.analise.seguranca.model;

public class CallGraphEdge {
    private String caller; 
    private String callee; 
    private boolean sensitive; // Exemplo: se detectado como função sensível

    public String getCaller() {
        return caller;
    }
    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCallee() {
        return callee;
    }
    public void setCallee(String callee) {
        this.callee = callee;
    }

    public boolean isSensitive() {
        return sensitive;
    }
    public void setSensitive(boolean sensitive) {
        this.sensitive = sensitive;
    }
}
