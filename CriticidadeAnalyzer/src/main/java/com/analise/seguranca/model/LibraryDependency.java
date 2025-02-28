package com.analise.seguranca.model;

public class LibraryDependency {
    private String name;
    private String version;
    // Você pode adicionar mais campos, como "isVulnerable" etc.

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
