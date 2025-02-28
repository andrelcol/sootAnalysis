package com.analise.seguranca.model;

public class ASTNode {
    private String type;        // Ex: "MethodDecl", "StringLiteral", etc.
    private String name;        // Nome do método/variável/classe
    private String location;    // Exemplo: "UsuarioService.java:45"
    private String stringValue; // Caso seja um StringLiteral

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getStringValue() {
        return stringValue;
    }
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
