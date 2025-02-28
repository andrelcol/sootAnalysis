package com.analise.seguranca.model;

import com.analise.seguranca.model.ASTNode;
import com.analise.seguranca.model.CallGraphEdge;
import com.analise.seguranca.model.LibraryDependency;

import java.util.ArrayList;
import java.util.List;

public class RootAST {
    private String projectName;
    private List<ASTNode> astNodes = new ArrayList<>();
    private List<CallGraphEdge> callGraphEdges = new ArrayList<>();
    private List<LibraryDependency> libraries = new ArrayList<>();

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ASTNode> getAstNodes() {
        return astNodes;
    }
    public List<CallGraphEdge> getCallGraphEdges() {
        return callGraphEdges;
    }
    public List<LibraryDependency> getLibraries() {
        return libraries;
    }
}
